package org.example.appmensajessecretos.dao;

import org.example.appmensajessecretos.domain.modelo.Mensaje;
import org.example.appmensajessecretos.domain.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DaoMessages {

    private final List<Mensaje> mensajes = new ArrayList<Mensaje>();


    public boolean send(String text, Usuario usuario,List<String> grupos, int grupoIndex) {

        return grupos.get(grupoIndex)
    }
}
