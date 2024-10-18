package org.example.appmensajessecretos.domain.servicio;

import org.example.appmensajessecretos.dao.DaoUsers;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.example.appmensajessecretos.utilities.Constantes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private DaoUsers dao;

    public UserService(DaoUsers dao) {
        this.dao = dao;
    }

    public Usuario findUser (Usuario user) {
        Usuario us = dao.findUser(user);
        if (dao.findUser(user) == null)
            return null;
        else if (!(us.getPassword().equals(user.getPassword())))
            return new Usuario();
        else {
            dao.addUser(user);
            return user;
        }
    }

    public void addUser(Usuario usuario) {
        dao.addUser(usuario);
    }

    public List<Usuario> loadUsers() {
        return dao.loadUsers();
    }
}
