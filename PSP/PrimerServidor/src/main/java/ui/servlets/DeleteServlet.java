package ui.servlets;

import domain.services.FoodService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "delete", value = "/delete")
public class DeleteServlet extends HttpServlet {

    private static final String DISH_ID = "dishId";
    private final FoodService foodService;

    @Inject
    public DeleteServlet(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       dispatchRequest(req,resp);
    }

    private void dispatchRequest (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter(DISH_ID) == null){
            resp.sendError(400,"Invalid ID");
        }
        else foodService.delete(req.getParameter(DISH_ID));
        resp.sendRedirect("home");
    }
}
