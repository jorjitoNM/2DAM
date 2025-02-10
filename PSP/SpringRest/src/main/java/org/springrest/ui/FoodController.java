package org.springrest.ui;

import jakarta.servlet.http.HttpServletResponse;
import org.springrest.common.Constantes;
import org.springrest.domain.model.Plato;
import org.springrest.domain.services.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping(Constantes.GET_ALL)
    public List<Plato> getAll() {
        return foodService.getDishes();
    }

    @PostMapping(Constantes.GET_DISH_URL)
    public ResponseEntity<Plato> get(@RequestParam String dishId) {
        if (dishId != null) {
            Plato p = foodService.getDish(Integer.parseInt(dishId));
            if (p != null) {
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(Constantes.DELETE_URL + "/{"+ Constantes.PATH_ID + "}")
    public ResponseEntity<Boolean> delete(@PathVariable(Constantes.PATH_ID) String id) {
        if (foodService.delete(Integer.valueOf(id)))
            return ResponseEntity.status(HttpServletResponse.SC_NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping(Constantes.UPDATE_URL)
    public ResponseEntity<Plato> update(@RequestParam(Constantes.DISH_ID) String dishId, @RequestParam(Constantes.NAME) String name, @RequestParam(Constantes.INGREDIENTS) List<String> ingredients) {
        if (dishId != null && name != null && ingredients != null)
            return ResponseEntity.ok(foodService.updateDish(new Plato(name, ingredients, Integer.parseInt(dishId))));
        return ResponseEntity.badRequest().build();
    }
}
