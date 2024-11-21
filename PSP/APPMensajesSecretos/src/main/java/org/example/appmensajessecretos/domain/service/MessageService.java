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
    private final Symmetric security;
    private final ValidateMessage messageValidator;
    private final ValidateUser userValidator;
    private final ValidateGroup groupValidator;


    public MessageService(DaoMessages dao, Symmetric security, ValidateMessage messageValidator, ValidateUser userValidator, ValidateGroup groupValidator) {
        this.dao = dao;
        this.security = security;
        this.messageValidator = messageValidator;
        this.userValidator = userValidator;
        this.groupValidator = groupValidator;
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
                                    .flatMap(nada2 -> security.encrypt(text, secretKey)
                                            .flatMap(encryptedText ->
                                                    security.encrypt(usuario.getName(), secretKey)
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
                                .flatMap(mensajes -> {
                                    List<Mensaje> decryptedMessages = new ArrayList<>();
                                    try {
                                        mensajes.forEach(m -> decryptedMessages.add(new Mensaje(
                                                security.decrypt(m.getContent(), secretKey)
                                                , m.getDate(), security.decrypt(m.getAuthor(), secretKey)
                                                , m.getGrupo())));
                                        return Either.right(decryptedMessages);
                                    } catch (EncryptingException e) {
                                        return Either.left(ServiceError.ERROR_DECRYPTING);
                                    }
                                });
                    }
                });
    }
}
