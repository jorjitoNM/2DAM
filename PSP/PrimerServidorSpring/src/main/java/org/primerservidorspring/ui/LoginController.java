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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private final UserService userService;
    private final MailComponent mailComponent;

    public LoginController(UserService userService, MailComponent mailComponent) {
        this.userService = userService;
        this.mailComponent = mailComponent;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return Constantes.LOGIN;
    }

    @PostMapping("/checkLogin")
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

    @PostMapping("/signUp")
    public String signUp(Model model, @RequestParam String email, @RequestParam String password) {
        String code = userService.signUp(new User(email, password));
        mailComponent.sendMail("jorge.novillo@educa.madrid.org", "Confirma tu correo", "<html><a herf=\"http://localhost:8080/confirm?code=" + code + "\">Comfirma tu correo pinchando aquí</a></html>");
        model.addAttribute(Constantes.CREATED,"true");
        return Constantes.LOGIN;
    }

    @GetMapping("/confirm")
    public String confirm(Model model, @RequestParam String code) {
        userService.confirmUser(code);
        return Constantes.LOGIN;
    }
}
