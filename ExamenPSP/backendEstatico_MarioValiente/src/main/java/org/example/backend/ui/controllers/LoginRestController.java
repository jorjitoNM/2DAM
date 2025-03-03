package org.example.backend.ui.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.example.backend.common.Constantes;
import org.example.backend.dao.modelo.AuthenticationResponse;
import org.example.backend.dao.modelo.User;
import org.example.backend.domain.servicio.ServicioJWT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constantes.API_LOGIN)
@RequiredArgsConstructor
public class LoginRestController {

    private final ServicioJWT servicioJWT;
    private final AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) {
        Authentication auth =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getNombre(),user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        return ResponseEntity.ok(new AuthenticationResponse(
                servicioJWT.generateAccessToken(user.getNombre(), 600),
                servicioJWT.generateRefreshToken(user.getNombre())
        ));
    }

    @PostMapping(Constantes.REFRESH)
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestHeader(Constantes.AUTHORIZATION) String authorizationHeader) {
        try {
            String refreshToken = authorizationHeader.substring(7);
            if (refreshToken.contains(Constantes.COMA)) {
                refreshToken = refreshToken.split(Constantes.COMA)[0].trim();
            }
            Jws<Claims> claims = servicioJWT.parseToken(refreshToken);
            String username = claims.getBody().getSubject();

            return ResponseEntity.ok(new AuthenticationResponse(
                    servicioJWT.generateAccessToken(username, 3600),
                    servicioJWT.generateRefreshToken(username)
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}
