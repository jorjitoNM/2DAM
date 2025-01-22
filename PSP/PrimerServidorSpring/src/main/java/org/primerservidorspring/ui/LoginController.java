package org.primerservidorspring.ui;

import jakarta.servlet.http.HttpSession;
import org.primerservidorspring.common.Constantes;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@Controller
public class LoginController {

    @GetMapping("/login")
    @Validated
    public String login (HttpSession session, @RequestAttribute String email, @RequestAttribute String password ) {
        if (session.getAttribute(Constantes.EMAIL) == null || session.getAttribute(Constantes.PASSWORD) == null) {
            session.setAttribute(Constantes.EMAIL,email);
            session.setAttribute(Constantes.PASSWORD,password);
            resp.sendRedirect(Constantes.HOME);
        }
        else if (!(session.getAttribute(Constantes.EMAIL).equals(email) && session.getAttribute(Constantes.PASSWORD).equals(password))) {
            resp.sendError(401,"Inicie sesion primero");
        } else {
            resp.sendRedirect(Constantes.HOME);
        }
        return null;
    }
}
