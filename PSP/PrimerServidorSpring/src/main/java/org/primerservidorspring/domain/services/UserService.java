package org.primerservidorspring.domain.services;

import org.primerservidorspring.dao.DaoUsers;
import org.primerservidorspring.domain.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {

    private  final DaoUsers daoUsers;

    public UserService(DaoUsers daoUsers) {
        this.daoUsers = daoUsers;
    }

    public String signUp (User user) {
        byte[] randomCode = new byte[16];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(randomCode);
        String code = Base64.getUrlEncoder().encodeToString(randomCode);
        daoUsers.singUp(new User(user.getEmail(),user.getPassword(),code));
        return code;
    }

    public void confirmUser(String code) {
        List<User> users = daoUsers.getAllUsers();
        users.stream().filter(u -> u.getCode().equals(code)).findFirst().ifPresent(u -> u.setActive(true));
    }

    public boolean login(User user) {
        User foundUser = daoUsers.getAllUsers().stream().filter(u -> u.getEmail().equals(user.getEmail()) && u.isActive()).findFirst().orElse(null);
        if (foundUser == null)
            return false;
        else
            return user.getPassword().equals(foundUser.getPassword());
    }
}
