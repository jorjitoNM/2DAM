package org.example.appmensajessecretos.domain.servicio;

import org.example.appmensajessecretos.dao.Dao;
import org.example.appmensajessecretos.domain.modelo.Grupo;

import java.util.ArrayList;

public class MessageService {
    public ArrayList<String> getMessages (Dao dao, Grupo group)  {
        return dao.getGrupos().stream().filter(g -> g.equals(group)).findFirst().orElse(null).getMessages();
    }

}
