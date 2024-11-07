package org.example.appmensajessecretos.dao;

import io.vavr.control.Either;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoGroups {

    private final DataBase dataBase;

    public DaoGroups(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public Either<Error, List<Grupo>> getGroups(Usuario user) {
        return dataBase.loadGroups().flatMap(grupos ->
                Either.right(grupos.stream()
                        .filter(grupo -> grupo.getMembers().contains(user.getName()))
                        .toList()));
    }

    public Either<Error, Void> joinGroup(Usuario user, Grupo group) {
        Either<Error,List<Grupo>> gruposEither = dataBase.loadGroups();
        return gruposEither
                .flatMap(grupos -> grupos.stream()
                        .filter(g -> g.getName().equals(group.getName()))
                        .findFirst()
                        .map(Either::<Error, Grupo>right)
                        .orElseGet(() -> Either.left(ServiceError.GROUP_NOT_FOUND))
                )
                .flatMap(foundGroup -> {
                    if (Boolean.TRUE.equals(group.getIsPrivate()))
                        return Either.left(DataInputError.GROUP_IS_PRIVATE);
                    else {
                        foundGroup.getMembers().add(user.getName());
                        return dataBase.saveGroups(gruposEither.get());
                    }
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
                    if (Boolean.TRUE.equals(group.getIsPrivate()))
                        group.getMembers().add(user.getName());
                    grupos.add(group);
                    return dataBase.saveGroups(grupos);
                });
    }

    public Either<Error, Void> deleteMember(String userName, String groupName) {
        Either<Error,List<Grupo>> gruposEither = dataBase.loadGroups();
        return gruposEither
                .flatMap(grupos -> grupos.stream()
                        .filter(g -> g.getName().equals(groupName))
                        .findFirst()
                        .map(Either::<Error, Grupo>right)
                        .orElseGet(() -> Either.left(ServiceError.GROUP_NOT_FOUND))
                )
                .flatMap(grupo -> {
                    if (grupo.getMembers().stream().anyMatch(u -> u.equals(userName)))
                        return Either.left(ServiceError.NOT_IN_GROUP);
                    else {
                        grupo.getMembers().removeIf(u -> u.equals(userName));
                        return dataBase.saveGroups(gruposEither.get());
                    }
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
            users.forEach(u -> grupo.getMembers().add(u.getName()));
            return dataBase.saveGroups(grupos);
        });
    });
}

    public Either<Error,Grupo> getGroup(Grupo group) {
        return dataBase.loadGroups()
                .flatMap(grupos ->
                        Either.right(grupos.stream().filter(g -> g.getName().equals(group.getName())).findFirst().orElse(null))
                );
    }
}
