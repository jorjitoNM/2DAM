package org.example.appmensajessecretos.dao;

import io.vavr.control.Either;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoUsers {
    private final DataBase dataBase;

    public DaoUsers(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public Either<Error,Void> saveUsers(List<Usuario> users) {
        return dataBase.saveUsers(users);
    }

    public Either<Error,List<Usuario>> loadUsers() {
        return dataBase.loadUsers();
    }
}
