package org.primerservidorspring.ui;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import org.primerservidorspring.common.Constantes;
import org.primerservidorspring.domain.model.Plato;
import org.primerservidorspring.domain.services.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @PostMapping(Constantes.DELETE_URL + "/{"+ Constantes.PATH_ID + "}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(foodService.delete(Integer.valueOf(id)));
    }

    @PostMapping(Constantes.UPDATE_URL)
    public ResponseEntity<Plato> update(@RequestParam String dishId, @RequestParam String nombre, @RequestParam List<String> ingredientes) {
        if (dishId != null && nombre != null && ingredientes != null)
            return ResponseEntity.ok(foodService.updateDish(new Plato(nombre, ingredientes, Integer.parseInt(dishId))));
        return ResponseEntity.badRequest().build();
    }
}
