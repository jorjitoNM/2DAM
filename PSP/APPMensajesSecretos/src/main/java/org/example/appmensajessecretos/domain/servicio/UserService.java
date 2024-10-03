package org.example.appmensajessecretos.domain.servicio;

import org.example.appmensajessecretos.dao.DaoUsers;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private DaoUsers dao;

    public UserService(DaoUsers dao) {
        this.dao = dao;
    }

    public boolean findUser (Usuario user) {
        return dao.findUser(user);
    }

    public void addUser(Usuario usuario) {
        dao.addUser(usuario);
    }
}
