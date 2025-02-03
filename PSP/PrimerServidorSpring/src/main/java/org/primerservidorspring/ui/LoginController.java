package org.primerservidorspring.ui;

import jakarta.servlet.http.HttpSession;
import org.primerservidorspring.common.Constantes;
import org.primerservidorspring.components.MailComponent;
import org.primerservidorspring.domain.model.User;
import org.primerservidorspring.domain.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(Constantes.LOGIN_URL)
    public String login() {
        return Constantes.LOGIN;
    }

    @PostMapping(Constantes.CHECK_LOGIN_URL)
    public String checkLogin(HttpSession session, Model model, @RequestParam String email, @RequestParam String password) {
        if (userService.login(new User(email, password))) {
            session.setAttribute(Constantes.LOGGED,true);
            return "redirect:/" + Constantes.HOME;
        }
        else {
            model.addAttribute(Constantes.ERROR, true);
            return Constantes.LOGIN;
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
