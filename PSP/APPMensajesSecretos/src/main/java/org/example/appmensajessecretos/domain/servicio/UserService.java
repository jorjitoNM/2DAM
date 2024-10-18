package org.example.appmensajessecretos.domain.servicio;

import org.example.appmensajessecretos.dao.DaoUsers;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final DaoUsers dao;

    public UserService(DaoUsers dao) {
        this.dao = dao;
    }

    public Usuario addUser (Usuario user) {
        List<Usuario> users = dao.loadUsers();
        Usuario us = users.stream().filter(u -> u.getName().equals(user.getName())).findFirst().orElse(null);
        if (us == null)
            return null;
        else if (!(us.getPassword().equals(user.getPassword())))
            return new Usuario();
        else {
            users.add(user);
            dao.saveUsers(users);
            return user;
        }
    }

    public List<Usuario> loadUsers(Usuario user) {
        return dao.loadUsers().stream().filter(u -> !(u.getName().equals(user.getName()))).toList();
    }
}
