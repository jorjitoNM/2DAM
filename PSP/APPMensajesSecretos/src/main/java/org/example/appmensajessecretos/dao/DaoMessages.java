package org.example.appmensajessecretos.dao;

import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Mensaje;
import org.example.appmensajessecretos.domain.modelo.MensajeGrupo;
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

    public boolean sendGroupMessage(String text, Usuario usuario, Grupo group) {
        List<MensajeGrupo> mensajes = dataBase.loadGroupMessages();
        mensajes.add(new MensajeGrupo(text, LocalDateTime.now(),usuario,group));
        return dataBase.saveGroupMessages(mensajes);
    }
    public boolean sendMessage(Mensaje mensaje) {
        List<Mensaje> mensajes = dataBase.loadMessages();
        mensajes.add(mensaje);
        return dataBase.saveMessages(mensajes);
    }

    public List<Mensaje> loadMessages(Usuario user, List<Usuario> receivers) {
        return dataBase.loadMessages().stream().filter(m -> m.getAuthor().getName().equals(user.getName()) && m.getReceivers().containsAll(receivers)).toList();
    }

    public List<MensajeGrupo> loadGroupMessages(Grupo group) {
        return dataBase.loadGroupMessages().stream().filter(m -> m.getGrupo().getName().equals(group.getName())).toList();
    }


}
