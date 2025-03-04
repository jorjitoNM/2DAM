package org.example.backend.dao.repositories;

import org.example.backend.common.Constantes;
import org.example.backend.domain.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDatabase {

    private final List<User> users = new ArrayList<>();

    public UserDatabase() {
        users.add(new User("admin", "$2a$12$L7Mg5yLrAtQJHemj/nvoqO7eechBNHP2TAYjPduS6LUpm4OTlSdjW", "ADMIN"));
        users.add(new User("user", "$2a$12$L7Mg5yLrAtQJHemj/nvoqO7eechBNHP2TAYjPduS6LUpm4OTlSdjW", "USER"));
        users.add(new User("userSinCosas", "$2a$12$L7Mg5yLrAtQJHemj/nvoqO7eechBNHP2TAYjPduS6LUpm4OTlSdjW", "USER"));
    }

    public User findByName(String nombre) {
        return users.stream().filter(user -> user.getName().equals(nombre)).findFirst().orElse(null);
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }

    public boolean checkPassword(String username, String password) {
        User user = users.stream().filter(u -> u.getName().equals(username)).findFirst().orElseThrow(
                () -> new UsernameNotFoundException(Constantes.USER_NOT_FOUND));
        return user.getPassword().equals(password);
    }
}
