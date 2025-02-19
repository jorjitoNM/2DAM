package org.springrest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springrest.common.Constantes;
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

    public Token getToken(String email) {
        String login = Jwts.builder()
                .claims()
                .add(Map.of(
                        Constantes.EMAIL, email
                ))
                .subject(email)
                .issuer("JorgeRest")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(Date.from(LocalDateTime.now().plusSeconds(20).atZone(ZoneId.systemDefault()).toInstant()))
                .and()
                .signWith(key)
                .compact();
        String refresh = Jwts.builder()
                .claims()
                .add(Map.of(
                        Constantes.EMAIL, email
                ))
                .subject(email)
                .issuer("JorgeRest")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(Date.from(LocalDateTime.now().plusSeconds(600000).atZone(ZoneId.systemDefault()).toInstant()))
                .and()
                .signWith(key)
                .compact();
        return new Token(login,refresh);
    }

    public void validateToken(String token) throws JwtException {
        Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseSignedClaims(token);
    }

    public String getEmail (String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
        return claims.getPayload().get(Constantes.EMAIL,String.class);
    }
}
