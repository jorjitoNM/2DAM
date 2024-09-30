package org.example.demojavafx.domain.validators;

import org.example.demojavafx.domain.modelo.User;

public class UserValidator {


    public boolean validateUser(User user){
        boolean ok = true;

        if (user.getUsername().isBlank() || user.getUsername().isEmpty()) {
            ok = false;
        }
        else if (user.getPassword().isBlank() || user.getPassword().isEmpty()) {
            ok = false;
        }


        return ok;
    }
}
