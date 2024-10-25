package org.example.appmensajessecretos.domain.servicio;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.DaoGroups;
import org.example.appmensajessecretos.domain.error.DataBaseError;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
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


    public Either<Error, Boolean> joinGroup (Usuario user, Grupo group) {
        List<Grupo> grupos = dao.loadGroups();
        Grupo foundGroup = grupos.stream()
                .filter(g -> g.getName().equals(group.getName()) && g.getPassword().equals(group.getPassword()))
                .findFirst()
                .orElse(null);
        if (foundGroup != group)
            return Either.left(DataBaseError.GROUP_NOT_FOUND);
        else if (foundGroup.getIsPrivate())
            return Either.left(DataInputError.GROUP_IS_PRIVATE);
        else
            if (foundGroup.getMembers().add(user))
                if (!dao.saveGroups(grupos))
                    return Either.left(DataBaseError.ACTION_FAILED);
                else
                    return Either.right(true);
            else
                return Either.left(DataBaseError.ERROR_JOINING_GROUP);
    }

    public boolean createGroup (Grupo group) {
        List<Grupo> grupos = dao.loadGroups();
        grupos.add(group);
        return dao.saveGroups(grupos);
    }

    public List<Grupo> getGroups (Usuario user) {
        return dao.getGroups(user);
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

    public Either<Error,Boolean> inviteUser (Grupo group, List<Usuario> users) {

    }
}
