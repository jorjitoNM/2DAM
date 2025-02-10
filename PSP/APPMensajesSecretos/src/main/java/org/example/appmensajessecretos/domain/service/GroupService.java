package org.example.appmensajessecretos.domain.service;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.DaoGroups;
import org.example.appmensajessecretos.dao.model.GroupRemote;
import org.example.appmensajessecretos.dao.model.UserRemote;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.example.appmensajessecretos.domain.validator.ValidateGroup;
import org.example.appmensajessecretos.domain.validator.ValidateUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GroupService {
    private final DaoGroups dao;
    private final ValidateUser userValidator;
    private final ValidateGroup groupValidator;
    private final PasswordEncoder passwordEncoder;

    public GroupService(DaoGroups dao, ValidateUser userValidator, ValidateGroup groupValidator, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.userValidator = userValidator;
        this.groupValidator = groupValidator;
        this.passwordEncoder = passwordEncoder;
    }

    public CompletableFuture<Either<Error, List<Grupo>>> getGroups(Usuario user) {
        return CompletableFuture.completedFuture(userValidator.validateUser(user)
                .flatMap(nada ->
                        dao.getGroups(user))
                .flatMap(groups -> {
                    if (groups.isEmpty())
                        return Either.left(ServiceError.NOT_IN_GROUPS);
                    else
                        return Either.right(groups.stream().map(GroupRemote::toGroup).toList());
                }));
    }


    public CompletableFuture<Either<Error, Void>> joinGroup(Usuario user, Grupo group) {
        return CompletableFuture.completedFuture(userValidator.validateUserIsLogged(user)
                .flatMap(nada -> userValidator.validateUser(user))
                .flatMap(nada -> groupValidator.validateGroup(group))
                .flatMap(nada -> {
                    Either<Error,GroupRemote> either = dao.getGroup(new GroupRemote(group));
                    if (either.get() == null)
                        return Either.left(ServiceError.GROUP_NOT_FOUND);
                    Grupo g = either.get().toGroup();
                    if (g.getMembers().stream().map(Usuario::getName).toList().contains(user.getName()))
                        return Either.left(ServiceError.ALREADY_IN_GROUP);
                    if (passwordEncoder.matches(group.getPassword(), g.getPassword()))
                        return Either.right(g);
                    else
                        return Either.left(DataInputError.INCORRECT_PASSWORD);
                })
                .flatMap(grupo -> dao.joinGroup(new UserRemote(user), new GroupRemote(group))));
    }

    public CompletableFuture<Either<Error, Void>> createGroup(Grupo group, Usuario user) {
        return CompletableFuture.completedFuture(userValidator.validateUserIsLogged(user)
                .flatMap(nada -> userValidator.validateUser(user))
                .flatMap(nada -> groupValidator.validateGroup(group))
                .flatMap(nada -> dao.createGroup(new GroupRemote(group.getName(), passwordEncoder.encode(group.getPassword()), group.getIsPrivate())
                        , new UserRemote(user))));
    }

    public CompletableFuture<Either<Error, Void>> deleteMember(String userName, String groupName, Usuario user) {
        return CompletableFuture.completedFuture(userValidator.validateUserIsLogged(user)
                .flatMap(nada -> {
                    if (userName.trim().isEmpty() || groupName.trim().isEmpty()) {
                        return Either.left(DataInputError.EMPTY_FIELDS);
                    } else {
                        return dao.deleteMember(userName, groupName);
                    }
                }));

    }

    public CompletableFuture<Either<Error, Void>> inviteUser(Grupo group, List<Usuario> users, Usuario user) {
        return CompletableFuture.completedFuture(userValidator.validateUserIsLogged(user)
                .flatMap(nada -> userValidator.validateUserList(users))
                .flatMap(nada -> groupValidator.validateGroup(group))
                .flatMap(nada -> {
                    if (Boolean.FALSE.equals(group.getIsPrivate()))
                        return Either.left(DataInputError.GROUP_IS_PRIVATE);
                    else
                        return dao.inviteUser(new GroupRemote(group), users.stream().map(Usuario::toUserRemote).toList());
                }));
    }
}
