package org.example.appmensajessecretos.dao;

import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Mensaje;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DaoMessages {

    private final DataBase dataBase;

    public DaoMessages(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean send(String text, Usuario usuario,Grupo group) {
        List<Mensaje> mensajes = dataBase.loadMessages();
        mensajes.add(new Mensaje(text, LocalDateTime.now(),usuario,group));
        return dataBase.saveMessages(mensajes);
    }

    public List<Mensaje> loadMessages(String groupName) {
        return dataBase.loadMessages().stream().
                filter(m -> m.getGroup().getName().equals(groupName)).toList();
    }
}
