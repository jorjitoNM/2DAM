package org.example.appmensajessecretos.domain.servicio;

import org.example.appmensajessecretos.dao.Dao;
import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class GroupService {
    public boolean joinGroup (Dao dao, Usuario user, Grupo group) {
        return dao.joinGroup(user, group);
    }
    public boolean createGroup (Dao dao, Grupo group) {
        return dao.createGroup(group);
    }
    public boolean findGroup(Dao dao, Grupo grupo) {
        return dao.findGroup(grupo);
    }
    public List<Grupo> getGroups (Dao dao, Usuario user) {
        return dao.getGrupos().stream().filter(g -> g.getMembers().contains(user)).toList();
    }
    public boolean deleteMember (Dao dao, String userName, String groupName) {
        Grupo grupo = dao.getGrupos().stream().filter(g -> g.getName().equals(groupName)).findFirst().orElse(null);
        if (grupo == null)
            return false;
        else
            return grupo.getMembers().removeIf(u -> u.getName().equals(userName));
    }

    public boolean findUser(Dao dao, String userName, String groupName) {
        Grupo grupo = dao.getGrupos().stream().filter(g -> g.getName().equals(groupName)).findFirst().orElse(null);
        if (grupo != null)
            return grupo.getMembers().stream().anyMatch(u -> u.getName().equals(userName));
        else
            return false;
    }
}
