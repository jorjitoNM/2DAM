package org.springrest.ui;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springrest.common.Constantes;
import org.springrest.domain.model.User;
import org.springrest.domain.services.UserService;
import org.springrest.security.JWTService;
import org.springframework.http.ResponseEntity;
import org.springrest.security.Token;
import org.springrest.ui.model.AuthUser;

@RestController
public class LoginController {

    private final UserService userService;
    private final JWTService tokenService;

    public LoginController(UserService userService, JWTService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(Constantes.LOGIN_URL)
    public ResponseEntity<Token> login(@RequestBody AuthUser user) {
        if (userService.login(new User(user.getEmail(), user.getPassword())))
            return ResponseEntity.ok(tokenService.getToken(user.getEmail()));
        else
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
    }

    @PostMapping(Constantes.REFRESH_URL)
    public ResponseEntity<Token> refresh (@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken) {
        return ResponseEntity.ok(tokenService.getToken(tokenService.getEmail(refreshToken.split(" ")[1].trim())));
    }

    @PostMapping(Constantes.SIGNUP_URL)
    public ResponseEntity<String> signUp(@RequestBody AuthUser user) {
        userService.signUp(new User(user.getEmail(), user.getPassword()));
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(Constantes.CHECK_MAIL);
    }

    @GetMapping(Constantes.CONFIRM_URL)
    public ResponseEntity<String> confirm(@RequestParam(Constantes.CODE) String code) {
        userService.confirmUser(code);
        return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(Constantes.ACCEPTED);
    }
}
