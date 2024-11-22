package org.example.appmensajessecretos.domain.service;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.DaoUsers;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.example.appmensajessecretos.domain.validator.ValidateUser;
import org.example.appmensajessecretos.utilities.security.Asymmetric;
import org.springframework.stereotype.Service;

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
                .flatMap(nada -> dao.getUser(user).flatMap(u -> {
                        if (u == null)
                            return Either.left(ServiceError.USER_NOT_FOUND);
                        else {
                            if (asymmetric.getPrivateKey(user).isRight())
                                return Either.right(user);
                            else
                                return Either.left(DataInputError.INCORRECT_PASSWORD);
                        }
                    })
                ));
    }

    public Either<Error, List<Usuario>> loadUsers (Usuario user) {
        return userValidator.validateUser(user)
                .flatMap(nada -> dao.loadUsers(user));
    }

    public CompletableFuture<Either<Error, Void>> addUser(Usuario user) {
        return CompletableFuture.completedFuture(userValidator.validateUser(user)
                .flatMap(nada -> {
                    if (dao.getUser(user).get() == null) {
                        if (asymmetric.saveUserKeys(user).isRight()) {
                            dao.addUser(user);
                            return Either.right(null);
                        } else
                            return Either.left(ServiceError.ERROR_GENERATING_KEYS);
                    } else
                        return Either.left(ServiceError.USER_ALREADY_EXIST);
                }));
    }

    public CompletableFuture<Either<Error, String>> saveGroupPassword (Usuario user, Grupo group) {
        return CompletableFuture.completedFuture(asymmetric.getPublicKey(user)
                .flatMap(userPublicKey -> asymmetric.cipher(group.getPassword(),userPublicKey))
                .flatMap(password -> dao.getUser(user)
                        .flatMap(foundUser -> {
                            foundUser.addGroupPassword(group.getName(),password);
                            return Either.right(password);
                        })));
    }
}