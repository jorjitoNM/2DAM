package ui.servlets;

import domain.model.Plato;
import domain.services.FoodService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import ui.listeners.ThymeLeafListener;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "update", value = "/update")
public class UpdateServlet extends HttpServlet {

    private final String DISH_ID = "id";
    private final String NAME = "nombre";

    private final FoodService foodService;

    @Inject
    public UpdateServlet(FoodService foodService) {
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

    private void dispatchRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(
                ThymeLeafListener.TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(request, response);
        WebContext context = new WebContext(webExchange);
        if (request.getParameter(DISH_ID) != null) {
            foodService.updateDish(new Plato(request.getParameter(NAME),new ArrayList<>(),Integer.parseInt(request.getParameter(DISH_ID))));
        }
        context.setVariable("dishes",foodService.getDishes());
        templateEngine.process("home", context, response.getWriter());
    }
}
