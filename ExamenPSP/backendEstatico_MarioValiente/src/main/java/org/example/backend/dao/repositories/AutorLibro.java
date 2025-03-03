package org.example.backend.dao.repositories;

import org.example.backend.dao.modelo.Autor;
import org.example.backend.dao.modelo.Libro;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AutorLibro {
    private final LibroRep libroRepositorio;
    private final AutorRep autorRepositorio;

    public AutorLibro(LibroRep libroRepositorio, AutorRep autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;

        Libro libro1 = libroRepositorio.obtenerLibroPorId(1);
        Autor autor1 = autorRepositorio.obtenerAutorPorId(1);
        asociarAutorALibro(libro1, autor1);

        Libro libro2 = libroRepositorio.obtenerLibroPorId(2);
        Autor autor2 = autorRepositorio.obtenerAutorPorId(2);
        asociarAutorALibro(libro2, autor2);
    }

    public void asociarAutorALibro(Libro libro, Autor autor) {
        libro.getAutores().add(autor);
        autor.getLibros().add(libro);
    }

    public void desasociarAutorDeLibro(Libro libro, Autor autor) {
        libro.getAutores().remove(autor);
        autor.getLibros().remove(libro);
    }

    public List<Autor> obtenerAutoresDeLibro(Libro libro) {
        return libro.getAutores();
    }

    public List<Libro> obtenerLibrosDeAutor(int autorId) {
        Autor autor1 = autorRepositorio.obtenerAutorPorId(autorId);
        return autor1.getLibros();
    }
}
