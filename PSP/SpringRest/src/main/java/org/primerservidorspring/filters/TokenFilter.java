package org.primerservidorspring.filters;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.primerservidorspring.common.Constantes;
import org.primerservidorspring.security.JWTService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class TokenFilter extends OncePerRequestFilter {
    private final JWTService tokenService;

    public TokenFilter(JWTService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Constantes.PETICION_INCOMPLETA);
        }
        final String token = header.split(" ")[1].trim();
        try {
            tokenService.validateToken(token);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return Constantes.LOGIN_URL.equals(path)
                || Constantes.CHECK_LOGIN_URL.equals(path)
                || Constantes.CONFIRM_URL.equals(path)
                || Constantes.SIGNUP_URL.equals(path);
    }
}
