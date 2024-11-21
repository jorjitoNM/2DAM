package org.example.appmensajessecretos.domain.service;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.DaoMessages;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.EncryptingException;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Mensaje;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.example.appmensajessecretos.utilities.security.Asymmetric;
import org.example.appmensajessecretos.utilities.security.Symmetric;
import org.example.appmensajessecretos.domain.validator.ValidateGroup;
import org.example.appmensajessecretos.domain.validator.ValidateMessage;
import org.example.appmensajessecretos.domain.validator.ValidateUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final DaoMessages dao;
    private final Symmetric symmetric;
    private final ValidateMessage messageValidator;
    private final ValidateUser userValidator;
    private final ValidateGroup groupValidator;
    private final Asymmetric asymmetric;


    public MessageService(DaoMessages dao, Symmetric symmetric, ValidateMessage messageValidator, ValidateUser userValidator, ValidateGroup groupValidator, Asymmetric asymmetric) {
        this.dao = dao;
        this.symmetric = symmetric;
        this.messageValidator = messageValidator;
        this.userValidator = userValidator;
        this.groupValidator = groupValidator;
        this.asymmetric = asymmetric;
    }

    public Either<Error, Void> sendMessage(String text, Usuario usuario, Grupo group, String secretKey) {
        return userValidator.validateUserIsLogged(usuario)
                .flatMap(nada -> groupValidator.validateGroup(group))
                .flatMap(nada -> {
                    if (Boolean.TRUE.equals(group.getIsPrivate()))
                        return messageValidator.validateMessage(new Mensaje(text, usuario.getName(), group.getName()))
                                .flatMap(nada2 -> dao.sendMessage(text, usuario, group));
                    else {
                        if (secretKey.isEmpty())
                            return Either.left(DataInputError.EMPTY_FIELDS);
                        else
                            return messageValidator.validateMessage(new Mensaje(text, usuario.getName(), group.getName()))
                                    .flatMap(nada2 -> symmetric.encrypt(text, secretKey)
                                            .flatMap(encryptedText ->
                                                    symmetric.encrypt(usuario.getName(), secretKey)
                                                            .flatMap(encryptedUsername ->
                                                                    dao.sendMessage(encryptedText,
                                                                            new Usuario(encryptedUsername, usuario.getPassword()),
                                                                            group
                                                                    ))));
                    }
                });
    }

    public Either<Error, List<Mensaje>> getMessages(Grupo group, String secretKey) {
        return groupValidator.validateGroup(group)
                .flatMap(nada -> {
                    if (Boolean.TRUE.equals(group.getIsPrivate())) {
                        return dao.loadMessages(group);
                    } else if (group.getName().trim().isBlank() || secretKey.trim().isBlank())
                        return Either.left(DataInputError.EMPTY_FIELDS);
                    else {
                        return dao.loadMessages(group)
                                .flatMap(mensajes -> decryptMessages(mensajes,secretKey));
                        return dao.loadMessages(group)
                                .flatMap(mensajes -> {
                                    List<Mensaje> decryptedMessages = new ArrayList<>();
                                    try {
                                        mensajes.forEach(m -> decryptedMessages.add(new Mensaje(
                                                symmetric.decrypt(m.getContent(), secretKey)
                                                , m.getDate(), symmetric.decrypt(m.getAuthor(), secretKey)
                                                , m.getGrupo())));
                                        return Either.right(decryptedMessages);
                                    } catch (EncryptingException e) {
                                        return Either.left(ServiceError.ERROR_DECRYPTING);
                                    }
                                });
                    }
                });
    }

    private Either<Error,List<Mensaje>> decryptMessages(List<Mensaje> mensajes, String secretKey) {
            // Usamos un Stream para procesar los mensajes
            List<Either<Error, Mensaje>> result = mensajes.stream()
                    .map(message -> new Mensaje(symmetric.decrypt(message.getContent(), secretKey),message.getAuthor(),message.getGrupo())) // Desencriptamos cada mensaje
                    .toList(); // Recopilamos los resultados en una lista

            // Verificamos si hay errores en la lista de resultados
            List<Error> errors = result.stream()
                    .filter(Either::isLeft) // Filtramos los mensajes con error
                    .map(Either::getLeft) // Extraemos los errores
                    .toList();

            if (!errors.isEmpty()) {
                // Si encontramos algún error, devolvemos el primer error
                return Either.left(errors.getFirst());
            }

            // Si no hubo errores, devolvemos los mensajes desencriptados
            List<Mensaje> validMessages = result.stream()
                    .filter(Either::isRight) // Filtramos los mensajes exitosos
                    .map(Either::get) // Extraemos los mensajes
                    .toList();

            return Either.right(validMessages); // Devolvemos la lista de mensajes exitosos
    }
}
