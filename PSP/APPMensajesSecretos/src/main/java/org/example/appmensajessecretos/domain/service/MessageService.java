package org.example.appmensajessecretos.domain.service;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.DaoMessages;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Mensaje;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.example.appmensajessecretos.domain.security.MainAesTest;
import org.example.appmensajessecretos.domain.validator.ValidateMessage;
import org.example.appmensajessecretos.domain.validator.ValidateUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final DaoMessages dao;
    private final MainAesTest security;
    private final ValidateMessage messageValidator;
    private final ValidateUser userValidator;


    public MessageService(DaoMessages dao, MainAesTest security, ValidateMessage messageValidator, ValidateUser userValidator) {
        this.dao = dao;
        this.security = security;
        this.messageValidator = messageValidator;
        this.userValidator = userValidator;
    }

    public Either<Error, Void> sendMessage(String text, Usuario usuario, Grupo group, String secretKey, Usuario user) {
        return userValidator.validateUserIsLogged(user)
                .flatMap(nada -> {
                    if (Boolean.TRUE.equals(group.getIsPrivate()))
                        return messageValidator.validateMessage(new Mensaje(text, usuario.getName(), group.getName()))
                                .flatMap(nada2 -> {
                                    String cipherText = security.encrypt(text, secretKey);
                                    Usuario cipherUser = new Usuario(security.encrypt(usuario.getName(), secretKey), usuario.getPassword());
                                    Grupo cipherGroup = new Grupo(security.encrypt(group.getName(), secretKey), group.getPassword(), group.getIsPrivate());
                                    return dao.sendMessage(cipherText, cipherUser, cipherGroup);
                                });
                    else
                        return messageValidator.validateMessage(new Mensaje(text, usuario.getName(), group.getName()))
                                .flatMap(nada2 -> dao.sendMessage(text, usuario, group));
                });
    }

    public Either<Error, List<Mensaje>> getMessages(Grupo group, String secretKey) {
        if (group.getName().trim().isEmpty() || secretKey.trim().isEmpty())
            return Either.left(DataInputError.EMPTY_FIELDS);
        else {
            if (Boolean.TRUE.equals(group.getIsPrivate())) {
                List<Mensaje> deCipherMessages = new ArrayList<>();
                return dao.loadMessages(group).flatMap(mensajes -> {
                    mensajes.forEach(m -> deCipherMessages.add(new Mensaje(
                            security.decrypt(m.getContent(), secretKey)
                            , m.getDate(), security.decrypt(m.getAuthor(), secretKey)
                            , security.decrypt(m.getGrupo(), secretKey))));
                    return Either.right(deCipherMessages);
                });
            } else
                return dao.loadMessages(group);
        }
    }
}
