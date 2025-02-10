package org.springrest.ui;

import jakarta.servlet.http.HttpServletResponse;
import org.springrest.common.Constantes;
import org.springrest.domain.model.User;
import org.springrest.domain.services.UserService;
import org.springrest.security.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserService userService;
    private final JWTService tokenService;

    public LoginController(UserService userService, JWTService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping(Constantes.LOGIN_URL)
    public ResponseEntity<String> login(@RequestParam(Constantes.EMAIL) String email, @RequestParam(Constantes.PASSWORD) String password) {
        if (userService.login(new User(email, password))) {
            return ResponseEntity.ok(tokenService.getToken(email,password));
        }
        else {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
    }

    @PostMapping(Constantes.SIGNUP_URL)
    public ResponseEntity<String> signUp(@RequestParam(Constantes.EMAIL) String email, @RequestParam(Constantes.PASSWORD) String password) {
        userService.signUp(new User(email, password));
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(Constantes.CHECK_MAIL);
    }

    @GetMapping(Constantes.CONFIRM_URL)
    public ResponseEntity<String> confirm(@RequestParam(Constantes.CODE) String code) {
        userService.confirmUser(code);
        return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(Constantes.ACCEPTED);
    }
}
