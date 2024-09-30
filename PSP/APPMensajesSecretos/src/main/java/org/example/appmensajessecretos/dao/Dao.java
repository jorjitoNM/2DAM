package org.example.appmensajessecretos.dao;

import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
public class Dao {
    private final Set<Grupo> grupos = new HashSet<>();
    private final Set<Usuario> usuarios = new HashSet<>();

    public Dao() {
        grupos.add(new Grupo("Grupo 1","1234"));
        grupos.add(new Grupo("Grupo 2", "1234"));
        grupos.add(new Grupo("Grupo 3", "1234"));
    }

    public boolean joinGroup(Usuario user, Grupo grupo) {
        Grupo foundGroup = grupos.stream()
                .filter(g -> g.getName().equals(grupo.getName()))
                .findFirst()
                .orElse(null);
        if (foundGroup == null)
            return false;
        else if (!foundGroup.getMembers().contains(user)) {
            foundGroup.getMembers().add(user);
            return true;
        }
        else
            return false;
    }

    public boolean createGroup(Grupo group) {
        return grupos.add(group);
    }

    public boolean findUser(Usuario user) {
        return usuarios.contains(user);
    }

    public boolean findGroup(Grupo grupo) {
        return grupos.stream().filter(g -> g.getName().equals(grupo.getName())).findFirst().orElse(null) != null;
    }

    public void addUser(Usuario usuario) {
        usuarios.add(usuario);
    }
}
