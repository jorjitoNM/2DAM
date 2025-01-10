package ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "login" ,value = "/")
public class LoginServlet extends HttpServlet {

    private final String EMAIL = "email";
    private final String PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        if (email == null || password == null) {
            req.getSession().setAttribute(EMAIL,email);
            req.getSession().setAttribute(PASSWORD,password);
            resp.sendRedirect("/home");
        }
        else if (!(req.getAttribute(EMAIL).equals(email) && req.getAttribute(PASSWORD).equals(password))) {
            resp.sendRedirect("/error");
        } else {

        }
    }
}
