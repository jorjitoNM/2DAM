package org.example.appmensajessecretos.domain.validator;

import io.vavr.control.Either;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.model.Mensaje;
import org.springframework.stereotype.Component;

@Component
public class ValidateMessage {
    public Either<Error,Void> validateMessage(Mensaje message) {
        if (message.getContent().trim().isBlank() || message.getAuthor().trim().isBlank() || message.getGrupo().trim().isBlank())
            return Either.left(DataInputError.EMPTY_FIELDS);
        else
            return Either.right(null);
    }
}
