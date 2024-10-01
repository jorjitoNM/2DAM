package org.example.appmensajessecretos.domain.servicio;

import javafx.collections.ObservableList;
import org.example.appmensajessecretos.dao.DaoGroups;
import org.example.appmensajessecretos.dao.DaoMessages;
import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MessageService {
    private final DaoMessages dao;

    public MessageService() {
        dao = new DaoMessages();
    }

    public boolean send(String text, Usuario usuario, String groupName) {
        return dao.send(text,usuario,groupName);
    }
}
