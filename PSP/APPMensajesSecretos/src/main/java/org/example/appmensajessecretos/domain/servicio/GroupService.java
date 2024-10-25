package org.example.appmensajessecretos.domain.servicio;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.DaoGroups;
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


    public Either<Error,List<Grupo>> getGroups (Usuario user) {
        return dao.getGroups(user);
    }

    public Either<Error, Boolean> joinGroup (Usuario user, Grupo group) {
        if (group.getIsPrivate()) return Either.left(DataInputError.GROUP_IS_PRIVATE);
        else return dao.joinGroup(user,group);
    }

    public Either<Error,Boolean> createGroup (Grupo group) {
        return dao.createGroup(group);
    }

    public Either<Error,Boolean> deleteMember (String userName, String groupName) {
        return dao.deleteMember(userName,groupName);
    }

    public Either<Error,Boolean> inviteUser (Grupo group, List<Usuario> users) {

    }
}
