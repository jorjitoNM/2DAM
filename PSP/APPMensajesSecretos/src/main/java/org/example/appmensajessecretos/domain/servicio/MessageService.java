package org.example.appmensajessecretos.domain.servicio;

import javafx.scene.Group;
import org.example.appmensajessecretos.dao.DaoMessages;
import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final DaoMessages dao;

    public MessageService(DaoMessages dao) {
        this.dao = dao;
    }

    public boolean send(String text, Usuario usuario, Grupo group) {
        return dao.send(text,usuario,group);
    }
}
