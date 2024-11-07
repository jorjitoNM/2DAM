package org.example.appmensajessecretos.domain.validator;

import io.vavr.control.Either;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.springframework.stereotype.Component;

@Component
public class ValidateGroup {
    public Either<Error,Void> validateGroup(Grupo group) {
        if (group.getName().trim().isEmpty() || group.getPassword().trim().isEmpty()) {
            return Either.left(DataInputError.EMPTY_FIELDS);
        }
        else {
            return Either.right(null);
        }
    }
}
