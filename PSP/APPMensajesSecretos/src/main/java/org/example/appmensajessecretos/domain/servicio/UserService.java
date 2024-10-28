package org.example.appmensajessecretos.domain.servicio;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.DaoUsers;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.modelo.Usuario;
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

    public Either<Error, Void> logIn(Usuario user) {
        return dao.loadUsers().flatMap(usuarios -> {
            Either<Error, Usuario> foundUser = usuarios.stream()
                    .filter(u -> u.getName().equals(user.getName()))
                    .findFirst()
                    .map(Either::<Error, Usuario>right)
                    .orElseGet(() -> Either.left(ServiceError.USER_NOT_FOUND));
            return foundUser.flatMap(u -> {
                if (passwordEncoder.matches(user.getPassword(),u.getPassword()))
                    return dao.saveUsers(usuarios);
                else
                    return Either.left(DataInputError.INCORRECT_PASSWORD);
            });
        });
    }

    public Either<Error, List<Usuario>> loadUsers(Usuario user) {
        return dao.loadUsers().flatMap(usuarios -> Either.right(usuarios.stream()
                .filter(u -> !u.getName().equals(user.getName())).toList()));
    }

    public Either<Error, Void> addUser(Usuario usuario) {
        Usuario finalUser = new Usuario(usuario.getName(), passwordEncoder.encode(usuario.getPassword()));
        return dao.loadUsers().flatMap(usuarios -> {
            usuarios.add(finalUser);
            return dao.saveUsers(usuarios);
        });
    }
}
