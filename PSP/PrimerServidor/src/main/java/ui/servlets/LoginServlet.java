package ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "login" ,value = "/login")
public class LoginServlet extends HttpServlet {

    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req,resp);
    }


    protected void dispatchRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        if (req.getAttribute(EMAIL) == null || req.getAttribute(PASSWORD) == null) {
            req.getSession().setAttribute(EMAIL,email);
            req.getSession().setAttribute(PASSWORD,password);
            resp.sendRedirect("home");
        }
        else if (!(req.getParameter(EMAIL).equals(email) && req.getParameter(PASSWORD).equals(password))) {
            resp.sendError(401,"Inicie sesion primero");
        } else {
            resp.sendRedirect("home");
        }
    }
}
