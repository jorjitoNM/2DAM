package org.example.appmensajessecretos.dao;

import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.example.appmensajessecretos.utilities.Constantes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoUsers {
    private final DataBase dataBase;

    public DaoUsers(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public String findUser(Usuario user) {
        List<Usuario> usuarios = dataBase.loadUsers();
        Usuario us = usuarios.stream().filter(u -> u.getName().equals(user.getName())).findFirst().orElse(null);
        if (us == null)
            return null;
        else if (!(us.getPassword().equals(user.getPassword())))
            return Constantes.CONTRASEÑA_INCORRECTA;
        else return Constantes.TODO_BIEN;
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
