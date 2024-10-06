package org.example.appmensajessecretos.dao;

import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoGroups {

    private final DataBase dataBase;

    public DaoGroups(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean joinGroup(Usuario user, Grupo grupo) {
        List<Grupo> grupos = dataBase.loadGroups();
        Grupo foundGroup = grupos.stream()
                .filter(g -> g.getName().equals(grupo.getName()) && g.getPassword().equals(grupo.getPassword()))
                .findFirst()
                .orElse(null);
        if (foundGroup == null)
            return false;
        else if (!foundGroup.getMembers().contains(user)) {
            foundGroup.getMembers().add(user);
            return dataBase.saveGroups(grupos);
        } else
            return false;
    }

    public boolean createGroup(Grupo group) {
        List<Grupo> grupos = dataBase.loadGroups();
        grupos.add(group);
        return dataBase.saveGroups(grupos);
    }


    public boolean findGroup(Grupo grupo) {
        return dataBase.loadGroups().stream().filter(g -> g.getName().equals(grupo.getName())).findFirst().orElse(null) != null;
    }

    public List<Grupo> getGroups(Usuario user) {
        return dataBase.loadGroups().stream()
                .filter(g -> g.getMembers().contains(user)).toList();
    }

    public boolean deleteMember(String userName, String groupName) {
        Grupo grupo = dataBase.loadGroups().stream()
                .filter(g -> g.getName().equals(groupName))
                .findFirst().orElse(null);
        if (grupo == null)
            return false;
        else
            return grupo.getMembers().removeIf(u -> u.getName().equals(userName));
    }

    public boolean findUser(String userName, String groupName) {
        Grupo grupo = dataBase.loadGroups().stream()
                .filter(g -> g.getName().equals(groupName))
                .findFirst().orElse(null);
        if (grupo != null)
            return grupo.getMembers().stream()
                    .anyMatch(u -> u.getName().equals(userName));
        else
            return false;
    }
}
