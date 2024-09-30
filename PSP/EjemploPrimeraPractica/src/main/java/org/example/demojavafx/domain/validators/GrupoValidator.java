package org.example.demojavafx.domain.validators;

import org.example.demojavafx.domain.modelo.Grupo;
import org.example.demojavafx.domain.modelo.User;

public class GrupoValidator {

    public boolean validateGrupo(Grupo user){
        boolean ok = true;

        if (user.getNombre().isBlank() || user.getNombre().isEmpty()) {
            ok = false;
        }
        else if (user.getPassword().isBlank() || user.getPassword().isEmpty()) {
            ok = false;
        }


        return ok;
    }

}
