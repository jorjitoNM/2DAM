package org.example.backend.ui;

import lombok.RequiredArgsConstructor;
import org.example.backend.common.Constantes;
import org.example.backend.domain.model.User;
import org.example.backend.security.jwt.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginRestController {

    private final JWTService tokenService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;


    @PostMapping(Constantes.LOGIN_URL)
    public ResponseEntity<String> login(@RequestBody User user) {
        Authentication auth =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        return ResponseEntity.ok(tokenService.generateToken(Map.of(
                Constantes.NAME, user.getName()
        ), userDetailsService.loadUserByUsername(user.getName())));
    }
}
