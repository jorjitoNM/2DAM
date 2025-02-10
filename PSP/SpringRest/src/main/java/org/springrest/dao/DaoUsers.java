package org.springrest.dao;

import org.springframework.stereotype.Repository;
import org.springrest.domain.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoUsers {

    private static final List<User> users = new ArrayList<>();

    public void signUp (User user) {
        users.add(new User(users.size()+1,user.getEmail(),user.getPassword(),user.getCode(),false));
    }

    public List<User> getAllUsers () {
        return users;
    }
}
