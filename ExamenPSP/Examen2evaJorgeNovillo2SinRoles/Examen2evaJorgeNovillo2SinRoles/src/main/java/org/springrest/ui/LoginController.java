package org.springrest.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springrest.common.Constantes;
import org.springrest.security.jwt.JWTService;
import org.springrest.security.jwt.Token;
import org.springrest.ui.model.AuthUser;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginController {

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

    @PostMapping(Constantes.REFRESH_URL)
    public ResponseEntity<String> refresh (@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(tokenService.generateLogin(Map.of(
                Constantes.EMAIL, email
        ), userDetailsService.loadUserByUsername(email)));
    }
}
