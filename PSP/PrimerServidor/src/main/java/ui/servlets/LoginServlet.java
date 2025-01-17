package ui.servlets;

import common.Constantes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "login" ,value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req,resp);
    }


    protected void dispatchRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter(Constantes.EMAIL);
        String password = req.getParameter(Constantes.PASSWORD);
        if (req.getAttribute(Constantes.EMAIL) == null || req.getAttribute(Constantes.PASSWORD) == null) {
            req.getSession().setAttribute(Constantes.EMAIL,email);
            req.getSession().setAttribute(Constantes.PASSWORD,password);
            resp.sendRedirect(Constantes.HOME);
        }
        else if (!(req.getParameter(Constantes.EMAIL).equals(email) && req.getParameter(Constantes.PASSWORD).equals(password))) {
            resp.sendError(401,"Inicie sesion primero");
        } else {
            resp.sendRedirect(Constantes.HOME);
        }
    }
}
