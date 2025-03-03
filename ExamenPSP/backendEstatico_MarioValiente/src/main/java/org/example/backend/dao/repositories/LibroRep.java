package org.example.backend.dao.repositories;

import org.example.backend.dao.modelo.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LibroRep {
    private static final List<Libro> libros = new ArrayList<>();

    public LibroRep() {
        if (libros.isEmpty()) {
            Libro libro1 = new Libro(1, "El Quijote", 1605, "Novela", "Francisco de Robles", new ArrayList<>());
            Libro libro2 = new Libro(2, "Cien años de soledad", 1967, "Realismo mágico", "Sudamericana", new ArrayList<>());
            libros.add(libro1);
            libros.add(libro2);
        }
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public Libro agregarLibro(Libro libro) {
        libros.add(libro);
        return libro;
    }

    public Libro obtenerLibroPorId(int id) {
        return libros.stream().filter(l -> l.getLibroId() == id).findFirst().orElse(null);
    }

    public Libro actualizarLibro(Libro libro) {
        eliminarLibro(libro.getLibroId());
        libros.add(libro);
        return libro;
    }

    public void eliminarLibro(int id) {
        libros.removeIf(l -> l.getLibroId() == id);
    }
}
