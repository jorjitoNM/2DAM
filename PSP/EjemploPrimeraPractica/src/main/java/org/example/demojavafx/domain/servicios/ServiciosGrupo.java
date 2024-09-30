package org.example.demojavafx.domain.servicios;

import org.example.demojavafx.data.DaoGrupos;
import org.example.demojavafx.domain.modelo.Grupo;
import org.example.demojavafx.domain.modelo.User;
import org.example.demojavafx.domain.validators.GrupoValidator;
import org.example.demojavafx.domain.validators.UserValidator;

import java.util.List;

public class ServiciosGrupo {

    private final DaoGrupos daoGrupos;
    private final GrupoValidator grupoValidator;

    public ServiciosGrupo() {
        this.daoGrupos = new DaoGrupos();
        grupoValidator = new GrupoValidator();
    }

    public boolean entrarEnGrupo(Grupo grupo, User user) {
        Grupo grupoDB = daoGrupos.getGrupoByName(grupo.getNombre());
        if (grupoDB != null && user != null && grupoDB.getPassword().equals(grupo.getPassword())) {
            daoGrupos.addUserToGrupo(grupoDB,user);
            return true;
        }
        return false;
    }

    public boolean registerGrupo(Grupo grupo) {
        if (grupoValidator.validateGrupo(grupo)) {
            return daoGrupos.registerGrupo(grupo);
        }
        return false;
    }

    public List<Grupo> getGruposOfUser(User user) {
        return daoGrupos.getGruposOfUser(user);
    }

}
