package org.springrest.domain.services;

import org.springrest.components.MailComponent;
import org.springrest.dao.UsersRepository;
import org.springrest.domain.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {

    private  final UsersRepository usersRepository;
    private final MailComponent mailComponent;

    public UserService(UsersRepository usersRepository, MailComponent mailComponent) {
        this.usersRepository = usersRepository;
        this.mailComponent = mailComponent;
    }

    public void signUp (User user) {
        byte[] randomCode = new byte[16];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(randomCode);
        String code = Base64.getUrlEncoder().encodeToString(randomCode);
        user.setCode(code);
        usersRepository.save(new User(user.getEmail(),user.getPassword(),user.getCode(),false));
        mailComponent.sendMail(user.getEmail(), "Confirma tu correo", "<html><a herf=\"http://localhost:8080/confirm?code=" + code + "\">Comfirma tu correo pinchando aquí</a></html>");
    }

    public void confirmUser(String code) {
        List<User> users = usersRepository.findAll();
        users.stream().filter(u -> u.getCode().equals(code)).findFirst().ifPresent(u -> u.setActive(true));
    }

    public boolean login(User user) {
        User foundUser = usersRepository.findAll().stream().filter(u -> u.getEmail().equals(user.getEmail()) && u.isActive()).findFirst().orElse(null);
        if (foundUser == null)
            return false;
        else
            return user.getPassword().equals(foundUser.getPassword());
    }
}
