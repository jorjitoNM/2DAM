package org.primerservidorspring.ui;

import org.primerservidorspring.common.Constantes;
import org.primerservidorspring.domain.model.Plato;
import org.primerservidorspring.domain.services.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping(Constantes.HOME_URL)
    public String getAll(Model model) {
        model.addAttribute(Constantes.DISHES, foodService.getDishes());
        return Constantes.HOME;
    }

    @PostMapping(Constantes.GET_DISH_URL)
    public String get(Model model, @RequestParam String dishId) {
        if (dishId != null) {
            Plato p = foodService.getDish(Integer.parseInt(dishId));
            if (p != null) {
                model.addAttribute(Constantes.DISH, p);
                return Constantes.UPDATE;
            }
        }
        return Constantes.HOME;
    }

    @PostMapping(Constantes.DELETE_URL)
    public String delete(@RequestParam Integer dishId) {
        if (dishId != null)
            foodService.delete(dishId);
        return "redirect:/" + Constantes.HOME;
    }

    @PostMapping(Constantes.UPDATE_URL)
    public String update(Model model, @RequestParam String dishId, @RequestParam String nombre, @RequestParam List<String> ingredientes) {
        if (dishId != null && nombre != null && ingredientes != null)
            foodService.updateDish(new Plato(nombre, ingredientes, Integer.parseInt(dishId)));
        model.addAttribute(Constantes.DISHES, foodService.getDishes());
        return "redirect:/" + Constantes.HOME;
    }
}
