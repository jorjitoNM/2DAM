package org.example.appmensajessecretos.domain.servicio;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.DaoMessages;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Mensaje;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final DaoMessages dao;

    public MessageService(DaoMessages dao) {
        this.dao = dao;
    }

    public Either<Error, Void> sendMessages(String text, Usuario usuario, Grupo group) {
        return dao.sendMessage(text,usuario,group);
    }

    public Either<Error,List<Mensaje>> getMessages(Grupo group) {
        return dao.loadMessages(group);
    }
}
