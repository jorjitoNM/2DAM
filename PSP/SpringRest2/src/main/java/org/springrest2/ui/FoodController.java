package org.springrest2.ui;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springrest2.common.Constantes;
import org.springrest2.domain.model.Plato;
import org.springrest2.domain.services.FoodService;

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
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        if (foodService.delete(Integer.valueOf(id)))
            return ResponseEntity.status(HttpServletResponse.SC_NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping(Constantes.UPDATE_URL)
    public ResponseEntity<Plato> update(@RequestParam String dishId, @RequestParam String nombre, @RequestParam List<String> ingredientes) {
        if (dishId != null && nombre != null && ingredientes != null)
            return ResponseEntity.ok(foodService.updateDish(new Plato(nombre, ingredientes, Integer.parseInt(dishId))));
        return ResponseEntity.badRequest().build();
    }
}
