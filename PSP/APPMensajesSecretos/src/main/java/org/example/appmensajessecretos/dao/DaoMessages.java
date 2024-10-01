package org.example.appmensajessecretos.dao;

import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Mensaje;
import org.example.appmensajessecretos.domain.modelo.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DaoMessages {

    private final List<Mensaje> mensajes = new ArrayList<Mensaje>();


    public boolean send(String text, Usuario usuario,String groupName) {
        Mensaje mensaje = new Mensaje(text, LocalDateTime.now(),usuario,new Grupo(groupName,""));
        return mensajes.add(new Mensaje(text, LocalDateTime.now(),usuario,new Grupo(groupName,"")));
    }
}
