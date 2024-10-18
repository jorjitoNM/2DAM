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

    public Usuario findUser(Usuario user) {
        return dataBase.loadUsers().stream().filter(u -> u.getName().equals(user.getName())).findFirst().orElse(null);
    }
    public void addUser(Usuario usuario) {
        List<Usuario> usuarios = dataBase.loadUsers();
        usuarios.add(usuario);
        dataBase.saveUsers(usuarios);
    }

    public List<Usuario> loadUsers() {
        return dataBase.loadUsers();
    }
}
