package org.springrest.ui;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springrest.common.Constantes;
import org.springrest.domain.model.User;
import org.springrest.domain.services.UserService;
import org.springrest.security.jwt.JWTService;
import org.springrest.security.jwt.Token;
import org.springrest.ui.model.AuthUser;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final JWTService tokenService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping(Constantes.LOGIN_URL)
    public ResponseEntity<Token> login(@RequestBody AuthUser user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        return ResponseEntity.ok(tokenService.generateToken(Map.of(
                Constantes.EMAIL, user.getEmail()
        ), userDetailsService.loadUserByUsername(user.getEmail())));
    }

    @GetMapping(Constantes.REFRESH_URL)
    public ResponseEntity<String> refresh() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(tokenService.generateLogin(Map.of(
                Constantes.EMAIL, email
        ), userDetailsService.loadUserByUsername(email)));
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
