package org.example.backend.ui.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.example.backend.common.Constantes;
import org.example.backend.dao.modelo.Libro;
import org.example.backend.domain.servicio.ServicioLibro;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constantes.API_LIBROS)
public class LibrosRestController {

    private final ServicioLibro servicioLibro;

    public LibrosRestController(ServicioLibro servicioLibro) {
        this.servicioLibro = servicioLibro;
    }

    @GetMapping("")
    @PreAuthorize("hasRole(" + Constantes.USER_ROL + ")")
    public ResponseEntity<List<Libro>> getLibros() {
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(servicioLibro.getAll());
    }

    @GetMapping(Constantes.ID)
    public ResponseEntity<Libro> getLibro(@PathVariable int id) {
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(servicioLibro.getLibroById(id));
    }


    @PutMapping()
    public ResponseEntity<Libro> updateLibro(@RequestBody Libro libro) {
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(servicioLibro.updateLibro(libro));
    }

    @DeleteMapping(Constantes.ID)
    @PreAuthorize("hasRole(" + Constantes.ADMIN + ")")
    public ResponseEntity<Void> deleteLibro(@PathVariable int id) {
        servicioLibro.deleteLibro(id);
        return ResponseEntity.status(HttpServletResponse.SC_NO_CONTENT).build();
    }
}
