package org.primerservidorspring.ui;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.primerservidorspring.common.Constantes;
import org.primerservidorspring.components.MailComponent;
import org.primerservidorspring.domain.model.User;
import org.primerservidorspring.domain.services.UserService;
import org.primerservidorspring.security.JWTService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@RestController
public class LoginController {

    private final UserService userService;
    private final JWTService tokenService;

    public LoginController(UserService userService, JWTService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping(Constantes.LOGIN_URL)
    public String login() {
        return Constantes.LOGIN;
    }

    @PostMapping(Constantes.CHECK_LOGIN_URL)
    public String checkLogin(HttpServletResponse response, @RequestParam String email, @RequestParam String password) {
        if (userService.login(new User(email, password))) {
            return tokenService.getToken(email,password);
        }
        else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "";
        }
    }

    @PostMapping(Constantes.SIGNUP_URL)
    public String signUp(Model model, @RequestParam String email, @RequestParam String password) {
        userService.signUp(new User(email, password));
        model.addAttribute(Constantes.CREATED,"true");
        return Constantes.LOGIN;
    }

    @GetMapping(Constantes.CONFIRM_URL)
    public String confirm(@RequestParam String code) {
        userService.confirmUser(code);
        return Constantes.LOGIN;
    }
}
