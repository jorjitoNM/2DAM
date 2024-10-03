package org.example.appmensajessecretos.dao;

import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class DaoUsers {
    private final DataBase dataBase;

    private final Set<Usuario> usuarios = new HashSet<>();

    public DaoUsers(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean findUser(Usuario user) {
        return usuarios.contains(user);
    }
    public void addUser(Usuario usuario) {
        usuarios.add(usuario);
    }

}
