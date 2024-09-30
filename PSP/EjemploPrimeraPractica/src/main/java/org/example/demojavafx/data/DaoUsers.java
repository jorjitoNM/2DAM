package org.example.demojavafx.data;

import org.example.demojavafx.domain.modelo.User;

import java.util.List;

public class DaoUsers {

    private final DataBase dataBase;

    public DaoUsers() {
        this.dataBase = new DataBase();
    }


    public User getUserbyName(String username) {
        return dataBase.loadUsuarios().stream().filter(user -> user.getUsername().equals(username))
            .findFirst().orElse(null);
    }

    public boolean registerUser(User usuarioNuevo) {

        List<User> usuarios = dataBase.loadUsuarios();
        usuarios.add(usuarioNuevo);
        return dataBase.saveUsuarios(usuarios);

    }

}
