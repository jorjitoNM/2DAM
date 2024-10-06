package org.example.appmensajessecretos.dao;

import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoUsers {
    private final DataBase dataBase;

    public DaoUsers(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean findUser(Usuario user) {
        List<Usuario> usuarios = dataBase.loadUsers();
        return usuarios.contains(user);
    }
    public void addUser(Usuario usuario) {
        List<Usuario> usuarios = dataBase.loadUsers();
        usuarios.add(usuario);
        dataBase.saveUsers(usuarios);
    }

}
