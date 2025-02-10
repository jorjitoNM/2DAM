package org.example.appmensajessecretos.dao;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.model.UserRemote;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoUsers {
    private final DataBase dataBase;

    public DaoUsers(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public Either<Error, List<UserRemote>> loadUsers(UserRemote user) {
        return dataBase.loadUsers().flatMap(usuarios -> Either.right(usuarios.stream()
                .filter(u -> !u.getName().equals(user.getName())).toList()));
    }

    public Either<Error, Void> addUser(UserRemote finalUser) {
        return dataBase.loadUsers().flatMap(usuarios -> {
            usuarios.add(finalUser);
            return dataBase.saveUsers(usuarios);
        });
    }

    public Either<Error, UserRemote> getUser(UserRemote user) {
        return dataBase.loadUsers().flatMap(usuarios -> Either.right(usuarios.stream()
                .filter(u -> u.getName().equals(user.getName()))
                .findFirst().orElse(null)));
    }

    public Either<Error, UserRemote> addGroupPassword(UserRemote userRemote, String password, Grupo group) {
        return dataBase.loadUsers()
                .flatMap(usuarios -> {
                    UserRemote user = usuarios.stream()
                            .filter(u -> u.getName().equals(userRemote.getName()))
                            .findAny().orElse(null);
                    if (user == null)
                        return Either.left(ServiceError.USER_NOT_FOUND);
                    else {
                        user.addGroupPassword(group.getName(),password);
                        return dataBase.saveUsers(usuarios)
                                .flatMap(nada -> Either.right(user));
                    }
                });

    }
}
