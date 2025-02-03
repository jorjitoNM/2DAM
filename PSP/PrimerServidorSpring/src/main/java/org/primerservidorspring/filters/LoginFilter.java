package org.primerservidorspring.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.primerservidorspring.common.Constantes;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getSession().getAttribute(Constantes.LOGGED) == null) {
            response.sendRedirect(Constantes.LOGIN_URL);
            return;
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
