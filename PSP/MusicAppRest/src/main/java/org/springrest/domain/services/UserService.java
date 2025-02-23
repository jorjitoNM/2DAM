package org.springrest.domain.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springrest.common.Constantes;
import org.springrest.components.MailComponent;
import org.springrest.dao.UsersRepository;
import org.springrest.domain.model.User;

import java.security.SecureRandom;
import java.util.Base64;

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
        User u = usersRepository.findUserByCode(code);
        u.setActive(true);
        usersRepository.save(u);
    }

    public User findUserByEmail(String email) {
        return usersRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(Constantes.USER_NOT_FOUND));
    }
}
