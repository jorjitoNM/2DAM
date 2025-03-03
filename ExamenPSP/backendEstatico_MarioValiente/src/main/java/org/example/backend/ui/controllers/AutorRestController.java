package org.example.backend.ui.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.example.backend.common.Constantes;
import org.example.backend.dao.modelo.Autor;
import org.example.backend.dao.modelo.Libro;
import org.example.backend.domain.servicio.ServicioAutor;
import org.example.backend.domain.servicio.ServicioJWT;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constantes.API_AUTORES)
public class AutorRestController {

    private final ServicioAutor servicioAutor;
    private final ServicioJWT servicioJWT;

    public AutorRestController(ServicioAutor servicioAutor, ServicioJWT servicioJWT) {
        this.servicioAutor = servicioAutor;
        this.servicioJWT = servicioJWT;
    }

    @GetMapping("")
    public ResponseEntity<List<Autor>> listarAutores() {
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(servicioAutor.getAll());
    }

    @PostMapping()
    public ResponseEntity<Autor> addAutor(@RequestBody Autor autor) {
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(servicioAutor.addAutor(autor));
    }

    @PostMapping(Constantes.ID)
    public ResponseEntity<Libro> addLibroDeAutor(@RequestBody Libro libro, @PathVariable int id) {
        Libro libro1 = servicioAutor.addLibroDeAutor(libro, id);
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(libro1);
    }

    @GetMapping(Constantes.LIBROS)
    public ResponseEntity<List<Libro>> getLibrosDeAutor(@PathVariable int id) {
        List<Libro> libros = servicioAutor.getLibrosDeAutor(id);
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(libros);
    }

    @GetMapping(Constantes.PATH_USER)
    public ResponseEntity<Autor> getAutor(@RequestHeader(Constantes.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        Autor autor = servicioAutor.getLibrosDeAutor(servicioJWT.getUsernameFromToken(token));
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(autor);
    }

}
