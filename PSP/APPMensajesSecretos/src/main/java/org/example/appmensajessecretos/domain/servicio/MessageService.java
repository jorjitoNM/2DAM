package org.example.appmensajessecretos.domain.servicio;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.DaoMessages;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Mensaje;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.example.appmensajessecretos.security.MainAesTest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class MessageService {
    private final DaoMessages dao;
    private final MainAesTest security;

    public MessageService(DaoMessages dao, MainAesTest security) {
        this.dao = dao;
        this.security = security;
    }

    private boolean anyEmptyFields (Object[] objects) {
        AtomicBoolean empty = new AtomicBoolean(false);
        Arrays.stream(objects).forEach(object -> {
            if (    object instanceof Usuario usuario
                    && (usuario.getName() == null || usuario.getName().isEmpty()
                    || usuario.getPassword() == null || usuario.getPassword().isEmpty())
                    || (object instanceof Grupo grupo
                    && (grupo.getName() == null || grupo.getName().isEmpty()
                    || grupo.getPassword() == null || grupo.getPassword().isEmpty()))
                    || (object instanceof String text
                    && (text.trim().isEmpty()))
            ) {
                empty.set(true);
            }
        });
        return empty.get();
    }

    public Either<Error, Void> sendMessages(String text, Usuario usuario, Grupo group, String secretKey) {
        if (anyEmptyFields(new Object[]{text,usuario,group,secretKey})) {
            return Either.left(DataInputError.EMPTY_FIELDS);
        }
        else {
            String cipherText = security.encrypt(text, secretKey);
            Usuario cipherUser = new Usuario(security.encrypt(usuario.getName(), secretKey), usuario.getPassword());
            Grupo cipherGroup = new Grupo(security.encrypt(group.getName(), secretKey), group.getPassword(), group.getIsPrivate());
            return dao.sendMessage(cipherText, cipherUser, cipherGroup);
        }
    }

    public Either<Error,List<Mensaje>> getMessages(Grupo group,String secretKey) {
        if (anyEmptyFields(new Object[]{group,secretKey})) {
            return Either.left(DataInputError.EMPTY_FIELDS);
        }
        else {
            List<Mensaje> deCipherMessages = new ArrayList<>();
            return dao.loadMessages(group).flatMap(mensajes -> {
                mensajes.forEach(m -> deCipherMessages.add(new Mensaje(
                        security.decrypt(m.getContent(), secretKey)
                        , m.getDate(), security.decrypt(m.getAuthor(), secretKey)
                        , security.decrypt(m.getGrupo(), secretKey))));
                return Either.right(deCipherMessages);
            });
        }
    }
}
