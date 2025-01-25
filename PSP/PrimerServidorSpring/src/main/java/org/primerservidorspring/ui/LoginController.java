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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    private final UserService userService;
    private final MailComponent mailComponent;

    public LoginController(UserService userService, MailComponent mailComponent) {
        this.userService = userService;
        this.mailComponent = mailComponent;
    }

    @GetMapping("/login")
    public RedirectView login (HttpSession session, @RequestAttribute String email, @RequestAttribute String password ) {
        if (userService.login(new User(email,password)))
            return new RedirectView(Constantes.HOME);
        else
            return new RedirectView(Constantes.ERROR);
    }

    @PostMapping("/signUp")
    public void signUp (Model model, @RequestParam String email, @RequestParam String password) {
        String code = userService.signUp(new User(email,password));
        mailComponent.sendMail("jorge.novillo@educa.madrid.org","Comfirma tu correo","<html><a herf=\"http://locahost:8080/confirm?code="+code+"\">Comfirma tu correo pinchando aquí</a></html>");
    }

    @PostMapping("/confirm")
    public RedirectView confirm (@RequestAttribute String code) {
        userService.confirmUser(code);
        return new RedirectView(Constantes.LOGIN);
    }
}
