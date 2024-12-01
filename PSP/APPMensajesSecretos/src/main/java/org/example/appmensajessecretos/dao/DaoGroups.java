package org.example.appmensajessecretos.dao;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.model.GroupRemote;
import org.example.appmensajessecretos.dao.model.UserRemote;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoGroups {

    private final DataBase dataBase;

    public DaoGroups(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public Either<Error, List<GroupRemote>> getGroups(Usuario user) {
        return dataBase.loadGroups().flatMap(grupos ->
                Either.right(grupos.stream()
                        .filter(grupo -> grupo.getMembers().stream().map(UserRemote::getName).toList()
                                .contains(user.getName()))
                        .toList()));
    }

    public Either<Error, Void> joinGroup(UserRemote user, GroupRemote group) {
        Either<Error,List<GroupRemote>> gruposEither = dataBase.loadGroups();
        return gruposEither
                .flatMap(grupos -> grupos.stream()
                        .filter(g -> g.getName().equals(group.getName()))
                        .findFirst()
                        .map(Either::<Error, GroupRemote>right)
                        .orElseGet(() -> Either.left(ServiceError.GROUP_NOT_FOUND))
                )
                .flatMap(foundGroup -> {
                    if (Boolean.TRUE.equals(group.getIsPrivate()))
                        return Either.left(DataInputError.GROUP_IS_PRIVATE);
                    else {
                        foundGroup.getMembers().add(user);
                        return dataBase.saveGroups(gruposEither.get());
                    }
                });
    }

    public Either<Error, Void> createGroup(GroupRemote group, UserRemote user) {
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
                        group.getMembers().add(user);
                    grupos.add(group);
                    return dataBase.saveGroups(grupos);
                });
    }

    public Either<Error, Void> deleteMember(String userName, String groupName) {
        Either<Error,List<GroupRemote>> gruposEither = dataBase.loadGroups();
        return gruposEither
                .flatMap(grupos -> grupos.stream()
                        .filter(g -> g.getName().equals(groupName))
                        .findFirst()
                        .map(Either::<Error, GroupRemote>right)
                        .orElseGet(() -> Either.left(ServiceError.GROUP_NOT_FOUND))
                )
                .flatMap(grupo -> {
                    if (grupo.getMembers().stream().map(UserRemote::getName).anyMatch(u -> u.equals(userName)))
                        return Either.left(ServiceError.NOT_IN_GROUP);
                    else {
                        grupo.getMembers().removeIf(u -> u.getName().equals(userName));
                        return dataBase.saveGroups(gruposEither.get());
                    }
                });
}

public Either<Error, Void> inviteUser(GroupRemote group, List<UserRemote> users) {
    return dataBase.loadGroups().flatMap(grupos -> {
        Either<Error, GroupRemote> gruposEither = grupos.stream()
                .filter(g -> g.getName().equals(group.getName()))
                .findFirst()
                .map(Either::<Error, GroupRemote>right)
                .orElseGet(() -> Either.left(ServiceError.GROUP_NOT_FOUND));

        return gruposEither.flatMap(grupo -> {
            users.forEach(u -> grupo.getMembers().add(u));
            return dataBase.saveGroups(grupos);
        });
    });
}

    public Either<Error,GroupRemote> getGroup(GroupRemote group) {
        return dataBase.loadGroups()
                .flatMap(grupos ->
                        Either.right(grupos.stream().filter(g -> g.getName().equals(group.getName())).findFirst().orElse(null))
                );
    }
}
