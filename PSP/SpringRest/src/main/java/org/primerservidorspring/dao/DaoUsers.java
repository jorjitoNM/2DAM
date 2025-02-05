package org.primerservidorspring.dao;

import org.primerservidorspring.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoUsers {

    private static final List<User> users = new ArrayList<>();

    public void singUp (User user) {
        users.add(new User(users.size()+1,user.getEmail(),user.getPassword(),user.getCode(),false));
    }

    public List<User> getAllUsers () {
        return users;
    }
}
