package org.example.backend.ui.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.backend.common.Constantes;
import org.example.backend.dao.modelo.Cultivo;
import org.example.backend.dao.modelo.Tierra;
import org.example.backend.domain.modelo.TierraDTO;
import org.example.backend.domain.servicio.ServicioTierras;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constantes.API_TIERRAS)
@RequiredArgsConstructor
public class TierrasRestController {

    private final ServicioTierras servicioTierras;

    @PostMapping
    @PreAuthorize("hasRole(" + Constantes.ADMIN_ROL + ")")
    public ResponseEntity<Tierra> addTierra(@RequestBody Tierra tierra) {
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(servicioTierras.addTierra(tierra));
    }

    @GetMapping
    @PreAuthorize("hasRole(" + Constantes.ADMIN_ROL + ")||(" + Constantes.MEXICANO_ROL + ")")
    public ResponseEntity<List<TierraDTO>> getTierras() {
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(servicioTierras.getTierras());
    }

    @GetMapping(Constantes.CULTIVOS_NOMBRE)
    @PreAuthorize("hasRole(" + Constantes.ADMIN_ROL + ")||(" + Constantes.MEXICANO_ROL + ")")
    public ResponseEntity<List<Cultivo>> getCultivosConNombreTierra(@PathVariable String nombre) {
        Tierra tierra = new Tierra(nombre);
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(servicioTierras.getCultivoDeTierra(tierra));
    }

    @GetMapping(Constantes.SIN_CULTIVOS)
    @PreAuthorize("hasRole(" + Constantes.ADMIN_ROL + ")||(" + Constantes.MEXICANO_ROL + ")")
    public ResponseEntity<List<Tierra>> getTierrasSinCultivos() {
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(servicioTierras.getTierrasSinCultivos());
    }
}
