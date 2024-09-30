package org.example.appmensajessecretos.dao;

import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class DaoGroups {
    private final Set<Grupo> grupos = new HashSet<>();

    public DaoGroups() {
        grupos.add(new Grupo("Grupo 1","1234"));
        grupos.add(new Grupo("Grupo 2", "1234"));
        grupos.add(new Grupo("Grupo 3", "1234"));
    }

    public boolean joinGroup(Usuario user, Grupo grupo) {
        Grupo foundGroup = grupos.stream()
                .filter(g -> g.getName().equals(grupo.getName()) && g.getPassword().equals(grupo.getPassword()))
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


    public boolean findGroup(Grupo grupo) {
        return grupos.stream().filter(g -> g.getName().equals(grupo.getName())).findFirst().orElse(null) != null;
    }
    public boolean send(String text, Usuario usuario, List<String> grupos, int grupoIndex) {
        this.grupos.stream().filter(g -> g.getName().equals(grupos.get(grupoIndex))).findAny().orElse(null);
        return
    }
}
