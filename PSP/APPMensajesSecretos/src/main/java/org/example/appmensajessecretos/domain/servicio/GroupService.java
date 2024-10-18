package org.example.appmensajessecretos.domain.servicio;

import org.example.appmensajessecretos.dao.DaoGroups;
import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final DaoGroups dao;

    public GroupService(DaoGroups dao) {
        this.dao = dao;
    }


    public boolean joinGroup (Usuario user, Grupo group) {
        List<Grupo> grupos = dao.loadGroups();
        Grupo foundGroup = grupos.stream()
                .filter(g -> g.getName().equals(group.getName()) && g.getPassword().equals(group.getPassword()))
                .findFirst()
                .orElse(null);
        if (foundGroup != group)
            return false;
        else if (!foundGroup.getMembers().contains(user)) {
            foundGroup.getMembers().add(user);
            return dao.saveGroups(grupos);
        } else
            return false;
    }

    public boolean createGroup (Grupo group) {
        List<Grupo> grupos = dao.loadGroups();
        grupos.add(group);
        return dao.saveGroups(grupos);
    }

    public boolean findGroup(Grupo grupo) {
        return dao.findGroup(grupo);
    }

    public List<Grupo> getGroups (Usuario user) {
        return dao.loadGroups().stream()
                .filter(g -> g.getMembers().contains(user)).toList();
    }
    public boolean deleteMember (String userName, String groupName) {
        List<Grupo> grupos = dao.loadGroups();
        Grupo grupo =grupos.stream()
                .filter(g -> g.getName().equals(groupName))
                .findFirst().orElse(null);
        if (grupo == null)
            return false;
        else {
            grupo.getMembers().removeIf(u -> u.getName().equals(userName));
            dao.saveGroups(grupos);
            return true;
        }
    }

    public boolean findUser(String userName, String groupName) {
        Grupo grupo = dao.loadGroups().stream()
                .filter(g -> g.getName().equals(groupName))
                .findFirst().orElse(null);
        if (grupo != null)
            return grupo.getMembers().stream()
                    .anyMatch(u -> u.getName().equals(userName));
        else
            return false;
    }
}
