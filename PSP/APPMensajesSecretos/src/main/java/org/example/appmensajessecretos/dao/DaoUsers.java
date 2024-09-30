package org.example.appmensajessecretos.dao;

import org.example.appmensajessecretos.domain.modelo.Usuario;

import java.util.HashSet;
import java.util.Set;

public class DaoUsers {
    private final Set<Usuario> usuarios = new HashSet<>();

    public boolean findUser(Usuario user) {
        return usuarios.contains(user);
    }
    public void addUser(Usuario usuario) {
        usuarios.add(usuario);
    }

}
