package org.example.appmensajessecretos.domain.servicio;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.DaoUsers;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final DaoUsers dao;
    private final PasswordEncoder passwordEncoder;

    public UserService(DaoUsers dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    private boolean validateUser(Usuario usuario) {
        return usuario.getName().trim().isEmpty() && usuario.getPassword().trim().isEmpty();
    }

    public Either<Error, Usuario> logIn(Usuario user) {
        if (validateUser(user)) {
            return Either.left(DataInputError.EMPTY_FIELDS);
        } else {
            return dao.getUser(user).flatMap(u -> {
                if (u == null)
                    return Either.left(ServiceError.USER_NOT_FOUND);
                else {
                    if (passwordEncoder.matches(user.getPassword(), u.getPassword()))
                        return Either.right(u);
                    else
                        return Either.left(DataInputError.INCORRECT_PASSWORD);
                }
            });
        }
    }

    public Either<Error, List<Usuario>> loadUsers(Usuario user) {
        if (validateUser(user)) {
            return Either.left(DataInputError.EMPTY_FIELDS);
        } else {
            return dao.loadUsers(user);
        }
    }

    public Either<Error, Void> addUser(Usuario user) {
        if (validateUser(user)) {
            return Either.left(DataInputError.EMPTY_FIELDS);
        } else {
            if (dao.getUser(user).get() == null) {
                Usuario finalUser = new Usuario(user.getName(), passwordEncoder.encode(user.getPassword()));
                return dao.addUser(finalUser);
            } else
                return Either.left(ServiceError.USER_ALREADY_EXIST);
        }
    }
}