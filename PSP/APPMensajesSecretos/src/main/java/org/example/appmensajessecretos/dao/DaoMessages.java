package org.example.appmensajessecretos.dao;

import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Mensaje;
import org.example.appmensajessecretos.domain.modelo.MensajeGrupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoMessages {

    private final DataBase dataBase;

    public DaoMessages(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean sendGroupMessage(String text, Usuario usuario, Grupo group) {
        List<MensajeGrupo> mensajes = dataBase.loadGroupMessages();
        mensajes.add(new MensajeGrupo(text, LocalDateTime.now(),usuario,group));
        return dataBase.saveGroupMessages(mensajes);
    }
    public boolean sendMessage(String text, Usuario usuario, ArrayList<Usuario> receivers) {
        List<Mensaje> mensajes = dataBase.loadMessages();
        mensajes.add(new Mensaje(text, LocalDateTime.now(),usuario,receivers));
        return dataBase.saveMessages(mensajes);
    }

    public List<Mensaje> loadMessages(Usuario user) {
        return dataBase.loadMessages().stream().filter(m -> m.getReceivers().contains(user)).toList();
    }

    public List<MensajeGrupo> loadGroupMessages(Grupo group) {
        return dataBase.loadGroupMessages().stream().filter(m -> m.getGrupo().getName() == group.getName()).toList();
    }


}
