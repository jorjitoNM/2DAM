package org.example.backend.ui.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.backend.common.Constantes;
import org.example.backend.dao.modelo.Cultivo;
import org.example.backend.dao.modelo.Tierra;
import org.example.backend.domain.servicio.ServicioCultivos;
import org.example.backend.domain.servicio.ServicioTierras;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constantes.API_CULTIVOS)
@RequiredArgsConstructor
public class CultivosRestController {

    private final ServicioCultivos servicioCultivos;
    private final ServicioTierras servicioTierra;

    @PostMapping
    @PreAuthorize("hasRole(" + Constantes.COLOMBIANO_ROL + ")")
    public ResponseEntity<Cultivo> addCultivo(@RequestBody Cultivo cultivo) {
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(servicioCultivos.addCultivo(cultivo));
    }

    @GetMapping(Constantes.NOMBRE_VARIABLE)
    @PreAuthorize("hasRole(" + Constantes.ADMIN_ROL + ")||(" + Constantes.COLOMBIANO_ROL + ")")
    public ResponseEntity<List<Tierra>> getTierraConCultivoEnConcreto(@PathVariable String nombre) {
        Cultivo cultivo = new Cultivo(nombre);
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(servicioTierra.getTierraConCultivo(cultivo));
    }

    @DeleteMapping(Constantes.NOMBRE_VARIABLE)
    @PreAuthorize("hasRole(" + Constantes.COLOMBIANO_ROL + ")")
    public ResponseEntity<Cultivo> deleteCultivo(@PathVariable String nombre) {
        Cultivo cultivo = new Cultivo(nombre);
        servicioCultivos.deleteCultivoSinTierra(cultivo);
        return ResponseEntity.status(HttpServletResponse.SC_NO_CONTENT).build();
    }
}
