package org.example.appmensajessecretos.domain.servicio;

import org.example.appmensajessecretos.dao.DaoUsers;
import org.example.appmensajessecretos.domain.modelo.Usuario;

public class UserService {
    private DaoUsers dao;

    public UserService() {
        dao = new DaoUsers();
    }

    public boolean findUser (Usuario user) {
        return dao.findUser(user);
    }

    public void addUser(Usuario usuario) {
        dao.addUser(usuario);
    }
}
