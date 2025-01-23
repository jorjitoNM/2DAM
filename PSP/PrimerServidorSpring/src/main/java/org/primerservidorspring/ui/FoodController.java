package org.primerservidorspring.ui;

import org.primerservidorspring.common.Constantes;
import org.primerservidorspring.domain.model.Plato;
import org.primerservidorspring.domain.services.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/home")
    public String getAll (Model model) {
       model.addAttribute(Constantes.DISHES,foodService.getDishes());
       return Constantes.HOME;
    }

    @PostMapping("/getDish")
    public String get (Model model, @RequestParam Integer dishId) {
        if (dishId != null) {
            Plato p = foodService.getDish(dishId);
            if (p != null) {
                model.addAttribute(Constantes.DISH, p);
                return Constantes.UPDATE;
            }
        }
        return Constantes.HOME;
    }

    @GetMapping("/delete")
    public RedirectView delete (Model model, @RequestParam Integer dishId) {
        if (dishId == null)
            return new RedirectView();
        else
            foodService.delete(dishId);
        return new RedirectView(Constantes.HOME);
    }

    @GetMapping("/update")
    public RedirectView update (Model model, @RequestParam Integer dishId, @RequestParam String name, @RequestParam List<String> ingredients) {
        if (dishId != null && name != null && ingredients != null) {
            foodService.updateDish(new Plato(name,ingredients,dishId));
        }
        model.addAttribute(Constantes.DISHES,foodService.getDishes());
        return new RedirectView(Constantes.HOME);
    }
}
