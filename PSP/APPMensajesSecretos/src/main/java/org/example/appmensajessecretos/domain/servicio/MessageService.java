package org.example.appmensajessecretos.domain.servicio;

import org.example.appmensajessecretos.dao.DaoMessages;
import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Mensaje;
import org.example.appmensajessecretos.domain.modelo.MensajeGrupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final DaoMessages dao;

    public MessageService(DaoMessages dao) {
        this.dao = dao;
    }

    public boolean sendGroupMessages(String text, Usuario usuario, Grupo group) {
        return dao.sendGroupMessage(text,usuario,group);
    }
    public boolean sendMessage(Mensaje mensaje) {
        return dao.sendMessage(mensaje);
    }
    public List<MensajeGrupo> getGroupMessages(Grupo group) {
        return dao.loadGroupMessages(group);
    }
    public List<Mensaje> getMessages(Usuario user, List<Usuario> receivers) {
        return dao.loadMessages(user,receivers);
    }
}
