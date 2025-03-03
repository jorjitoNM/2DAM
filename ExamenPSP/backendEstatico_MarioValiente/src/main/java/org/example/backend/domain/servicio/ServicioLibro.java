package org.example.backend.domain.servicio;

import org.example.backend.common.Constantes;
import org.example.backend.dao.modelo.Libro;
import org.example.backend.dao.repositories.LibroRep;
import org.example.backend.domain.errors.RecursoNotFound;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioLibro {
    private final LibroRep libroRepository;

    public ServicioLibro(LibroRep libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> getAll() {
        return libroRepository.getLibros();
    }

    public Libro getLibroById(int libroId) {
        return Optional.ofNullable(libroRepository.obtenerLibroPorId(libroId))
                .orElseThrow(() -> new RecursoNotFound(Constantes.LIBRO_NO_ENCONTRADO));
    }

    public Libro updateLibro(Libro libroActualizado) {
        Libro libro = Optional.ofNullable(libroRepository.obtenerLibroPorId(libroActualizado.getLibroId()))
                .orElseThrow(() -> new RuntimeException(Constantes.LIBRO_NO_ENCONTRADO));
        libroActualizado.setAutores(libro.getAutores());
        return libroRepository.agregarLibro(libroActualizado);
    }

    public void deleteLibro(int libroId) {
        libroRepository.eliminarLibro(libroId);
    }
}
