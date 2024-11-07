package org.example.appmensajessecretos.domain.validator;

import io.vavr.control.Either;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidateUser {
    public Either<Error,Void> validateUser (Usuario user) {
        if (user.getName().trim().isEmpty() || user.getPassword().trim().isEmpty()) {
            return Either.left(DataInputError.EMPTY_FIELDS);
        }
        else {
            return Either.right(null);
        }
    }

    public Either<Error,Void> validateUserIsLogged (Usuario user) {
        if (user == null)
            return Either.left(DataInputError.NOT_LOGGED);
        else
            return Either.right(null);
    }

    public Either<Error, Void> validateUserList(List<Usuario> users) {
        if (users.isEmpty()) {
            return Either.left(DataInputError.EMPTY_FIELDS);
        }
        else
            return Either.right(null);
    }
}
