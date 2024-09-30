package org.example.appmensajessecretos.domain.servicio;

import org.example.appmensajessecretos.dao.Dao;
import org.example.appmensajessecretos.domain.modelo.Usuario;

public class UserService {

    public boolean findUser (Dao dao, Usuario user) {
        return dao.findUser(user);
    }

    public void addUser(Dao dao, Usuario usuario) {
        dao.addUser(usuario);
    }
}
