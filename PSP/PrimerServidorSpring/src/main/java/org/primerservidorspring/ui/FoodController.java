package org.primerservidorspring.ui;

import org.primerservidorspring.common.Constantes;
import org.primerservidorspring.domain.model.Plato;
import org.primerservidorspring.domain.services.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.util.Collections;

@Controller
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/home")
    public void getAll () {
        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(
                ThymeLeafListener.TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(request, response);
        WebContext context = new WebContext(webExchange);
        context.setVariable("dishes",foodService.getDishes());
        templateEngine.process("home", context, response.getWriter());
    }

    @GetMapping("/getDish")
    public void get () {
        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(
                ThymeLeafListener.TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(request, response);
        WebContext context = new WebContext(webExchange);
        if (request.getParameter(Constantes.DISH_ID) != null) {
            Plato p = foodService.getDish(request.getParameter(Constantes.DISH_ID));
            if (p != null) {
                context.setVariable("dish", p);
                templateEngine.process(Constantes.UPDATE, context, response.getWriter());
            }
        }
    }

    @GetMapping("/delete")
    public void delete () {
        if (req.getParameter(Constantes.DISH_ID) == null){
            resp.sendError(400,"Invalid ID");
        }
        else foodService.delete(req.getParameter(DISH_ID));
        resp.sendRedirect("home");
    }

    @GetMapping("/update")
    public void update () {
        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(
                ThymeLeafListener.TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(request, response);
        WebContext context = new WebContext(webExchange);
        if (request.getParameter(Constantes.DISH_ID) != null &&
                request.getParameter(Constantes.NAME) != null &&
                request.getParameter(Constantes.INGREDIENTES) != null) {
            foodService.updateDish(new Plato(request.getParameter(Constantes.NAME),
                    Collections.singletonList(request.getParameter(Constantes.INGREDIENTES)),
                    Integer.parseInt(request.getParameter(Constantes.DISH_ID))));
        }
        context.setVariable("dishes",foodService.getDishes());
        templateEngine.process(Constantes.HOME, context, response.getWriter());
        response.sendRedirect(Constantes.HOME);
    }
}
