package org.example.appmensajessecretos.dao;

import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoGroups {

    private final DataBase dataBase;

    public DaoGroups(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public List<Grupo> loadGroups () {
        return dataBase.loadGroups();
    }

    public boolean saveGroups (List<Grupo> groups) {
        return dataBase.saveGroups(groups);
    }

    public boolean findGroup(Grupo grupo) {
        return dataBase.loadGroups().stream().filter(g -> g.getName().equals(grupo.getName())).findFirst().orElse(null) != null;
    }
}
