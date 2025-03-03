package org.example.backend.domain.servicio;

import org.example.backend.common.Constantes;
import org.example.backend.dao.modelo.Autor;
import org.example.backend.dao.modelo.Libro;
import org.example.backend.dao.repositories.AutorLibro;
import org.example.backend.dao.repositories.AutorRep;
import org.example.backend.domain.errors.RecursoNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioAutor {
    private final AutorLibro autorLibroRepository;
    private final AutorRep autorRep;

    public ServicioAutor(AutorLibro autorLibroRepository, AutorRep autorRepRepository) {
        this.autorLibroRepository = autorLibroRepository;
        this.autorRep = autorRepRepository;
    }

    public List<Autor> getAll() {
        return autorRep.getAutores();
    }

    public Autor getAutorById(int autorId) {
        return Optional.ofNullable(autorRep.obtenerAutorPorId(autorId)).orElseThrow(() ->
                new RecursoNotFound(Constantes.AUTOR_NO_ENCONTRADO));
    }

    public Autor addAutor(Autor autor) {
        return autor;
    }

    public Libro addLibroDeAutor(Libro libro, int autorId) {
        Autor autor = getAutorById(autorId);
        autorLibroRepository.asociarAutorALibro(libro, autor);
        return libro;
    }

    public List<Libro> getLibrosDeAutor(int autorId) {
        return autorLibroRepository.obtenerLibrosDeAutor(autorId);
    }

    public Autor getLibrosDeAutor(String username) {
        return autorRep.obtenerAutorPorNombre(username);
    }
}
