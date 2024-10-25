package org.example.appmensajessecretos.dao;

import io.vavr.control.Either;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoGroups {

    private final DataBase dataBase;

    public DaoGroups(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public Either<Error,List<Grupo>> getGroups (Usuario user) {
        return dataBase.loadGroups().stream()
                .filter(g -> g.getMembers().contains(user)).toList();
    }

    public List<Grupo> loadGroups () {
        return dataBase.loadGroups();
    }

    public boolean saveGroups (List<Grupo> groups) {
        return dataBase.saveGroups(groups);
    }
}
