package org.example.appmensajessecretos.domain.service;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.DaoGroups;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.example.appmensajessecretos.domain.validator.ValidateGroup;
import org.example.appmensajessecretos.domain.validator.ValidateUser;
import org.example.appmensajessecretos.utilities.security.Asymmetric;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final DaoGroups dao;
    private final ValidateUser userValidator;
    private final ValidateGroup groupValidator;
    private final PasswordEncoder passwordEncoder;
    private final Asymmetric asymmetric;


    public GroupService(DaoGroups dao, ValidateUser userValidator, ValidateGroup groupValidator, PasswordEncoder passwordEncoder, Asymmetric asymmetric) {
        this.dao = dao;
        this.userValidator = userValidator;
        this.groupValidator = groupValidator;
        this.passwordEncoder = passwordEncoder;
        this.asymmetric = asymmetric;
    }

    public Either<Error, List<Grupo>> getGroups(Usuario user) {
        return userValidator.validateUser(user)
                .flatMap(nada ->
                        dao.getGroups(user))
                .flatMap(groups -> {
                    if (groups.isEmpty())
                        return Either.left(ServiceError.NOT_IN_GROUPS);
                    else
                        return Either.right(groups);
                });
    }


    public Either<Error, Void> joinGroup(Usuario user, Grupo group) {
        return userValidator.validateUserIsLogged(user)
                .flatMap(nada -> userValidator.validateUser(user))
                .flatMap(nada -> groupValidator.validateGroup(group))
                .flatMap(nada ->  {
                    Grupo g = dao.getGroup(group).get();
                    if (g == null)
                        return Either.left(ServiceError.GROUP_NOT_FOUND);
                    else if (g.getMembers().contains(user.getName()))
                        return Either.left(ServiceError.ALREADY_IN_GROUP);
                    else if (passwordEncoder.matches(group.getPassword(), g.getPassword()))
                        return Either.right(g);
                    else
                        return Either.left(DataInputError.INCORRECT_PASSWORD);
                })
                .flatMap(grupo -> dao.joinGroup(user, group));
    }

    public Either<Error, Void> createGroup(Grupo group, Usuario user) {
        return userValidator.validateUserIsLogged(user)
                .flatMap(nada -> userValidator.validateUser(user))
                .flatMap(nada -> groupValidator.validateGroup(group))
                .flatMap(nada -> dao.createGroup(new Grupo(group.getName(),passwordEncoder.encode(group.getPassword()),group.getIsPrivate()), user));
    }

    public Either<Error, Void> deleteMember(String userName, String groupName, Usuario user) {
        return userValidator.validateUserIsLogged(user)
                .flatMap(nada -> {
                    if (userName.trim().isEmpty() || groupName.trim().isEmpty()) {
                        return Either.left(DataInputError.EMPTY_FIELDS);
                    } else {
                        return dao.deleteMember(userName, groupName);
                    }
                });

    }

    public Either<Error, Void> inviteUser(Grupo group, List<Usuario> users, Usuario user) {
        return userValidator.validateUserIsLogged(user)
                .flatMap(nada -> userValidator.validateUserList(users))
                .flatMap(nada -> groupValidator.validateGroup(group))
                .flatMap(nada -> {
                    if (Boolean.FALSE.equals(group.getIsPrivate()))
                        return Either.left(DataInputError.GROUP_IS_PRIVATE);
                    else
                        return dao.inviteUser(group, users);
                });
    }
}
