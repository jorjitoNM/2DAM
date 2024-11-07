package org.example.appmensajessecretos.domain.servicio;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.DaoGroups;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class GroupService {
    private final DaoGroups dao;

    public GroupService(DaoGroups dao) {
        this.dao = dao;
    }

    private boolean anyEmptyFields (Object[] objects) {
        AtomicBoolean empty = new AtomicBoolean(false);
        Arrays.stream(objects).forEach(object -> {
            if (object instanceof Usuario usuario
                    && (usuario.getName() == null || usuario.getName().isEmpty()
                    || usuario.getPassword() == null || usuario.getPassword().isEmpty())
                    || (object instanceof Grupo grupo
                    && (grupo.getName() == null || grupo.getName().isEmpty()
                    || grupo.getPassword() == null || grupo.getPassword().isEmpty()))
            ) {
                empty.set(true);
            }
        });
        return empty.get();
    }

    public Either<Error, List<Grupo>> getGroups(Usuario user) {
        if (anyEmptyFields(new Object[]{user})) {
            return Either.left(DataInputError.EMPTY_FIELDS);
        } else {
            return dao.getGroups(user).flatMap(groups -> {
                if (groups.isEmpty())
                    return Either.left(ServiceError.NOT_IN_GROUPS);
                else
                    return Either.right(groups);
            });
        }
    }


    public Either<Error, Void> joinGroup(Usuario user, Grupo group) {
        if (anyEmptyFields(new Object[]{user,group})) {
            return Either.left(DataInputError.EMPTY_FIELDS);
        } else {
            return dao.joinGroup(user, group);
        }
    }

    public Either<Error, Void> createGroup(Grupo group, Usuario user) {
        if (anyEmptyFields(new Object[]{user,group})) {
            return Either.left(DataInputError.EMPTY_FIELDS);
        } else {
            return dao.createGroup(group, user);
        }
    }

    public Either<Error, Void> deleteMember(String userName, String groupName) {
        if (userName.trim().isEmpty() || groupName.trim().isEmpty()) {
            return Either.left(DataInputError.EMPTY_FIELDS);
        } else {
            return dao.deleteMember(userName, groupName);
        }
    }

    public Either<Error, Void> inviteUser(Grupo group, List<Usuario> users) {
        if (anyEmptyFields(new Object[]{group}) && users.isEmpty()) {
            return Either.left(DataInputError.EMPTY_FIELDS);
        } else {
            if (Boolean.FALSE.equals(group.getIsPrivate()))
                return Either.left(DataInputError.GROUP_IS_PRIVATE);
            else
                return dao.inviteUser(group, users);
        }
    }
}
