package org.primerservidorspring.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Component
public class JWTService {

    private final Key key;

    public JWTService(Key key) {
        this.key = key;
    }

    public String getToken(String email, String password) {
        return Jwts.builder()
                .claims()
                .add(Map.of(
                        "email", email,
                        "password", password
                ))
                .subject(email)
                .issuer("JorgeRest")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(Date.from(LocalDateTime.now().plusSeconds(60).atZone(ZoneId.systemDefault()).toInstant()))
                .and()
                .signWith(key)
                .compact();
    }

    public void validateToken(String token) throws JwtException {
        Jwts.parser().setSigningKey(key).build();
    }
}
