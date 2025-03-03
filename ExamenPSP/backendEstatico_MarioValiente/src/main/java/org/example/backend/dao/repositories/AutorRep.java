package org.example.backend.dao.repositories;

import org.example.backend.dao.modelo.Autor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AutorRep {

    private static final List<Autor> autores = new ArrayList<>();

    public AutorRep() {
        if (autores.isEmpty()) {
            Autor autor1 = new Autor(1, "Miguel de Cervantes", "1547-09-29", "1616-04-22", "España", new ArrayList<>());
            Autor autor2 = new Autor(2, "Gabriel García Márquez", "1927-03-06", "2014-04-17", "Colombia", new ArrayList<>());
            Autor autor3 = new Autor(3, "admin", "1927-03-06", "2014-04-17", "Colombia", new ArrayList<>());
            autores.add(autor1);
            autores.add(autor2);
            autores.add(autor3);
        }
    }

    public List<Autor> getAutores() {
        return new ArrayList<>(autores);
    }

    public Autor obtenerAutorPorId(int id) {
        return autores.stream().filter(a -> a.getAutorId() == id).findFirst().orElse(null);
    }

    public Autor obtenerAutorPorNombre(String username) {
        return autores.stream().filter(a -> a.getNombre().equals(username)).findFirst().orElse(null);
    }
}
