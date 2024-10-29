package org.example.appmensajessecretos.dao;

import io.vavr.control.Either;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DaoGroups {

    private final DataBase dataBase;

    public DaoGroups(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public Either<Error, List<Grupo>> getGroups(Usuario user) {
        return dataBase.loadGroups().map(grupos -> grupos.stream()
                .filter(grupo -> grupo.getMembers().contains(user))
                .collect(Collectors.toList())).flatMap(groups -> {
            if (groups.isEmpty()) {
                return Either.left(ServiceError.NOT_IN_GROUPS);
            } else {
                return Either.right(groups);
            }
        });
    }

    public Either<Error, Void> joinGroup(Usuario user, Grupo group) {
        return dataBase.loadGroups()
                .flatMap(grupos -> {
                    Either<Error, Grupo> grupoEither = grupos.stream()
                            .filter(g -> g.getName().equals(group.getName()))
                            .findFirst()
                            .map(Either::<Error, Grupo>right)
                            .orElseGet(() -> Either.left(ServiceError.GROUP_NOT_FOUND));

                    return grupoEither.flatMap(foundGroup -> {
                        if (Boolean.TRUE.equals(group.getIsPrivate()))
                            return Either.left(DataInputError.GROUP_IS_PRIVATE);
                        else {
                            foundGroup.getMembers().add(user);
                            return dataBase.saveGroups(grupos);
                        }
                    });
                });
    }

    public Either<Error, Void> createGroup(Grupo group, Usuario user) {
        return dataBase.loadGroups()
                .flatMap(grupos -> {
                    if (grupos.stream().anyMatch(g -> g.getName().equals(group.getName()))) {
                        return Either.left(ServiceError.GROUP_ALREADY_EXISTS);
                    } else {
                        return Either.right(grupos);
                    }
                })
                .flatMap(grupos -> {
                    if (group.getIsPrivate())
                        group.getMembers().add(user);
                    grupos.add(group);
                    return dataBase.saveGroups(grupos);
                });
    }

    public Either<Error, Void> deleteMember(String userName, String groupName) {
        return dataBase.loadGroups().flatMap(grupos -> {

            Either<Error, Grupo> gruposEither = grupos.stream()
                    .filter(g -> g.getName().equals(groupName))
                    .findFirst()
                    .map(Either::<Error, Grupo>right)
                    .orElseGet(() -> Either.left(ServiceError.GROUP_NOT_FOUND));

            return gruposEither.flatMap(grupo -> {
                if (grupo.getMembers().stream().anyMatch(u -> u.getName().equals(userName)))
                    return Either.left(ServiceError.NOT_IN_GROUP);
                else {
                    grupo.getMembers().removeIf(u -> u.getName().equals(userName));
                    return dataBase.saveGroups(grupos);
                }
            });
        });
    }

    public Either<Error, Void> inviteUser(Grupo group, List<Usuario> users) {
        return dataBase.loadGroups().flatMap(grupos -> {
            Either<Error, Grupo> gruposEither = grupos.stream()
                    .filter(g -> g.getName().equals(group.getName()))
                    .findFirst()
                    .map(Either::<Error, Grupo>right)
                    .orElseGet(() -> Either.left(ServiceError.GROUP_NOT_FOUND));

            return gruposEither.flatMap(grupo -> {
                grupo.getMembers().addAll(users);
                return dataBase.saveGroups(grupos);
            });
        });
    }
}
