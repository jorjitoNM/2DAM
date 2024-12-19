package ui;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/main")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req,resp);
    }

    private void dispatchRequest (HttpServletRequest req, HttpServletResponse resp) {
        Integer random = (Integer)req.getSession().getAttribute("random");

        Integer intento =(Integer)req.getSession().getAttribute("intentos");

        if (intento == null) {
            intento = 1;
        }

    }
}
