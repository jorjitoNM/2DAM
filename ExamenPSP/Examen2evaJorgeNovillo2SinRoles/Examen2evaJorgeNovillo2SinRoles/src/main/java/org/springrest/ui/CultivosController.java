package org.springrest.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springrest.common.Constantes;
import org.springrest.domain.model.Cultivo;
import org.springrest.domain.model.Tierra;
import org.springrest.domain.services.CultivosService;

import java.util.List;

@RestController
@RequestMapping(Constantes.CULTIVOS_URL)
@RequiredArgsConstructor
public class CultivosController {
    private final CultivosService service;


    @GetMapping(Constantes.GET_URL)
    public List<Tierra> get (@RequestBody Cultivo cultivo) {
        return service.get(cultivo);
    }


    @PutMapping(Constantes.ADD_URL)
    public void addCultivo (@RequestBody Cultivo cultivo) {
        service.addCultivo(cultivo);
    }


    @PreAuthorize("hasRole(" + Constantes.ADMIN + ")")
    @PostMapping(Constantes.DELETE_URL)
    public boolean delete (@RequestBody Cultivo cultivo) {
        return service.deleteCultivoColombiano(cultivo);
    }
}
