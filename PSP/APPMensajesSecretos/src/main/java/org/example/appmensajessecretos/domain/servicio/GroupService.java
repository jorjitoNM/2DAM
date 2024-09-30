package org.example.appmensajessecretos.domain.servicio;

import org.example.appmensajessecretos.dao.DaoGroups;
import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;

import java.util.List;

public class GroupService {
    private final DaoGroups dao;

    public GroupService() {
        dao = new DaoGroups();
    }

    public boolean joinGroup (Usuario user, Grupo group) {
        return dao.joinGroup(user, group);
    }
    public boolean createGroup (Grupo group) {
        return dao.createGroup(group);
    }
    public boolean findGroup(Grupo grupo) {
        return dao.findGroup(grupo);
    }
    public List<Grupo> getGroups (Usuario user) {
        return dao.getGrupos().stream()
                .filter(g -> g.getMembers().contains(user)).toList();
    }
    public boolean deleteMember (String userName, String groupName) {
        Grupo grupo = dao.getGrupos().stream()
                .filter(g -> g.getName().equals(groupName))
                .findFirst().orElse(null);
        if (grupo == null)
            return false;
        else
            return grupo.getMembers().removeIf(u -> u.getName().equals(userName));
    }

    public boolean findUser(String userName, String groupName) {
        Grupo grupo = dao.getGrupos().stream()
                .filter(g -> g.getName().equals(groupName))
                .findFirst().orElse(null);
        if (grupo != null)
            return grupo.getMembers().stream()
                    .anyMatch(u -> u.getName().equals(userName));
        else
            return false;
    }

    public Grupo getGrupoByIndex(int groupIndex, Usuario user) {

    }
}
