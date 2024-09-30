package org.example.demojavafx.domain.servicios;

import org.example.demojavafx.data.DaoUsers;
import org.example.demojavafx.domain.modelo.User;
import org.example.demojavafx.domain.validators.UserValidator;

public class ServiciosUser {

    private final UserValidator userValidator;
    private final DaoUsers daoUsers;

    public ServiciosUser() {
        this.daoUsers = new DaoUsers();
        this.userValidator = new UserValidator();
    }

    public boolean loginUser(User user) {
        if (userValidator.validateUser(user)) {
            User userDB = daoUsers.getUserbyName(user.getUsername());
            return userDB != null && userDB.getPassword().equals(user.getPassword());
        }
        return false;
    }

    public boolean registerUser(User user) {
        if (userValidator.validateUser(user)) {
            return daoUsers.registerUser(user);
        }
        return false;
    }
}
