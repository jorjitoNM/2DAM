package org.springrest.dao;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.springrest.common.Constantes;
import org.springrest.domain.errors.NotFoundException;
import org.springrest.domain.model.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class UsersDatabase {

    private List<User> users;

    public UsersDatabase() {
        this.users = new ArrayList<>();
        users.add(new User("admin", "$2a$12$L7Mg5yLrAtQJHemj/nvoqO7eechBNHP2TAYjPduS6LUpm4OTlSdjW", "ADMIN"));
        users.add(new User("user", "$2a$12$L7Mg5yLrAtQJHemj/nvoqO7eechBNHP2TAYjPduS6LUpm4OTlSdjW", "USER"));
    }

    public User findUserByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElseThrow(() -> new NotFoundException(Constantes.USER_NOT_FOUND));
    }
}
