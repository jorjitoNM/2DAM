package org.primerservidorspring.ui;

import jakarta.servlet.http.HttpServletResponse;
import org.primerservidorspring.common.Constantes;
import org.primerservidorspring.domain.model.User;
import org.primerservidorspring.domain.services.UserService;
import org.primerservidorspring.security.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    private final UserService userService;
    private final JWTService tokenService;

    public LoginController(UserService userService, JWTService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(Constantes.LOGIN_URL)
    public String login(HttpServletResponse response, @RequestParam String email, @RequestParam String password) {
        if (userService.login(new User(email, password))) {
            return tokenService.getToken(email,password);
        }
        else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "";
        }
    }

    @PostMapping(Constantes.SIGNUP_URL)
    public ResponseEntity<Map<String,String>> signUp(@RequestParam String email, @RequestParam String password) {
        userService.signUp(new User(email, password));
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(new HashMap<>());
    }

    @GetMapping(Constantes.CONFIRM_URL)
    public ResponseEntity<Map<String,String>> confirm(@RequestParam String code) {
        userService.confirmUser(code);
        return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(new HashMap<>());
    }
}
