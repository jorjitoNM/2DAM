package ui.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ui.servlets.LoginServlet;


import java.io.IOException;


@WebFilter(filterName = "FilterRoleUser",urlPatterns = {"/PrivateOS/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // filter login
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute(LoginServlet.PASSWORD) == null) {
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,"QUE TE DEN");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
