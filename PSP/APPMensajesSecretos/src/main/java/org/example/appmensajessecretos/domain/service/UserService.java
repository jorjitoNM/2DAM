package org.example.appmensajessecretos.domain.service;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.DaoUsers;
import org.example.appmensajessecretos.dao.model.UserRemote;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.example.appmensajessecretos.domain.validator.ValidateUser;
import org.example.appmensajessecretos.utilities.security.Asymmetric;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    private final DaoUsers dao;
    private final ValidateUser userValidator;
    private final Asymmetric asymmetric;

    public UserService(DaoUsers dao, ValidateUser userValidator, Asymmetric asymmetric) {
        this.dao = dao;
        this.asymmetric = asymmetric;
        this.userValidator = userValidator;
    }


    public CompletableFuture<Either<Error, Usuario>> logIn(Usuario user) {
        return CompletableFuture.completedFuture(userValidator.validateUser(user)
                .flatMap(nada -> dao.getUser(new UserRemote(user)).flatMap(u -> {
                            if (u == null)
                                return Either.left(ServiceError.USER_NOT_FOUND);
                            else {
                                return asymmetric.getPrivateKey(user)
                                        .flatMap(pk -> Either.right(u.toUser(user.getPassword())));
                            }
                        })
                ));
    }

    public CompletableFuture<Either<Error, List<Usuario>>> loadUsers(Usuario user) {
        return CompletableFuture.completedFuture(userValidator.validateUser(user)
                .flatMap(nada -> dao.loadUsers(new UserRemote(user))
                        .flatMap(users -> Either.right(parseUsers(users)))));
    }

    private List<Usuario> parseUsers(List<UserRemote> users) {
        List<Usuario> parsedUsers = new ArrayList<>();
        users.forEach(u -> parsedUsers.add(new Usuario(u)));
        return parsedUsers;
    }

    public CompletableFuture<Either<Error, Void>> addUser(Usuario user) {
        return CompletableFuture.completedFuture(userValidator.validateUser(user)
                .flatMap(nada -> {
                    if (dao.getUser(new UserRemote(user)).get() == null) {
                        return asymmetric.saveUserKeys(user)
                                .flatMap(nada2 -> {
                                    dao.addUser(new UserRemote(user));
                                    return Either.right(null);
                                });
                    } else
                        return Either.left(ServiceError.USER_ALREADY_EXIST);
                }));
    }

    public CompletableFuture<Either<Error, Usuario>> saveGroupPassword(Usuario user, Grupo group) {
        return CompletableFuture.completedFuture(asymmetric.getPublicKey(user)
                .flatMap(userPublicKey -> asymmetric.cipher(group.getPassword(), userPublicKey))
                .flatMap(password -> dao.addGroupPassword(new UserRemote(user), password, group)
                        .flatMap(dbUser -> Either.right(dbUser.toUser(user.getPassword())))));
    }
}