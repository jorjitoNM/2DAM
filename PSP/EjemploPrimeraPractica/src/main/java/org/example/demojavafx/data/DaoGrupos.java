package org.example.demojavafx.data;

import org.example.demojavafx.domain.modelo.Grupo;
import org.example.demojavafx.domain.modelo.User;

import java.util.List;

public class DaoGrupos {

    private final DataBase dataBase;

    public DaoGrupos() {
        this.dataBase = new DataBase();
    }


    public Grupo getGrupoByName(String grupoName) {
        return dataBase.loadGrupos().stream().filter(grupo -> grupo.getNombre().equals(grupoName))
                .findFirst().orElse(null);
    }
    public List<Grupo> getGruposOfUser(User user) {
        return dataBase.loadGrupos().stream().filter(grupo -> grupo.getUsuarios().contains(user))
                .toList();
    }


    public boolean addUserToGrupo(Grupo grupo, User user) {
        List<Grupo> grupos = dataBase.loadGrupos();
        Grupo grupoDB = grupos.stream().filter(g -> g.getNombre().equals(grupo.getNombre()))
                .findFirst().orElse(null);
        if (grupoDB != null) {
            grupoDB.getUsuarios().add(user);
            dataBase.saveGrupos(grupos);
            return true;
        }
        return false;
    }

    public boolean registerGrupo(Grupo grupoNuevo) {

        List<Grupo> grupos = dataBase.loadGrupos();
        grupos.add(grupoNuevo);
        return dataBase.saveGrupos(grupos);

    }

}
