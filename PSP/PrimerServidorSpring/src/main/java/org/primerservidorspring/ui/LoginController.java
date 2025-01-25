package org.primerservidorspring.ui;

import jakarta.servlet.http.HttpSession;
import org.primerservidorspring.common.Constantes;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public RedirectView login (HttpSession session, @RequestAttribute String email, @RequestAttribute String password ) {
        if (session.getAttribute(Constantes.EMAIL) == null || session.getAttribute(Constantes.PASSWORD) == null) {
            session.setAttribute(Constantes.EMAIL,email);
            session.setAttribute(Constantes.PASSWORD,password);
            session.setAttribute(Constantes.LOGGED,true);
            return new RedirectView(Constantes.HOME);
        }
        else if (!(session.getAttribute(Constantes.EMAIL).equals(email) && session.getAttribute(Constantes.PASSWORD).equals(password))) {
            return new RedirectView(Constantes.ERROR);
        } else {
            session.setAttribute(Constantes.LOGGED,true);
            return new RedirectView(Constantes.HOME);
        }
    }

    @PostMapping("/confirm")
    public RedirectView confirm (@RequestAttribute String email, @RequestAttribute String password ) {
        return null;
    }
}
