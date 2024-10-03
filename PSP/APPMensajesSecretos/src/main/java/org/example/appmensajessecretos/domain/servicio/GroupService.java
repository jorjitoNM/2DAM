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
        return dao.joinGroup(user, group);
    }

    public boolean createGroup (Grupo group) {
        return dao.createGroup(group);
    }

    public boolean findGroup(Grupo grupo) {
        return dao.findGroup(grupo);
    }

    public List<Grupo> getGroups (Usuario user) {
        return dao.getGroups(user);
    }
    public boolean deleteMember (String userName, String groupName) {
        return dao.deleteMember(userName,groupName);
    }

    public boolean findUser(String userName, String groupName) {
        return dao.findUser(userName,groupName);
    }
}
