package org.example.backend.dao.repositories;

import org.example.backend.dao.modelo.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRep {

    private final List<User> users = new ArrayList<>();

    public UserRep() {
        users.add(new User("admin", "$2b$12$oKx8jvjkO.3QODD3g9xsj.K4c2/muhszrRIgZ14gUqPkCAaI3KHaS", true, "A001", "ADMIN"));
        users.add(new User("user1", "$2b$12$oKx8jvjkO.3QODD3g9xsj.K4c2/muhszrRIgZ14gUqPkCAaI3KHaS", true, "U001", "USER"));
        users.add(new User("user2", "$2b$12$oKx8jvjkO.3QODD3g9xsj.K4c2/muhszrRIgZ14gUqPkCAaI3KHaS", true, "U002", "USER"));
    }

    public void save(User user) {
        users.add(user);
    }

    public User findByName(String nombre) {
        return users.stream().filter(user -> user.getNombre().equals(nombre)).findFirst().orElse(null);
    }

    public List<User> findAll() {
        return users;
    }
}
