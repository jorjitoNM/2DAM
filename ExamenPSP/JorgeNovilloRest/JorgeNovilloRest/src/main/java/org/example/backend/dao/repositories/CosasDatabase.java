package org.example.backend.dao.repositories;

import org.example.backend.common.Constantes;
import org.example.backend.domain.errors.NoEsTuCosaException;
import org.example.backend.domain.errors.NoTienesCosasException;
import org.example.backend.domain.errors.NotFoundException;
import org.example.backend.domain.model.Cosa;
import org.example.backend.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CosasDatabase {

    private final List<Cosa> cosas = new ArrayList<>();

    public CosasDatabase() {
        cosas.add(new Cosa(1, "cosa1", new User("admin", "$2a$12$L7Mg5yLrAtQJHemj/nvoqO7eechBNHP2TAYjPduS6LUpm4OTlSdjW", "ADMIN")));
        cosas.add(new Cosa(2, "cosa2", new User("user", "$2a$12$L7Mg5yLrAtQJHemj/nvoqO7eechBNHP2TAYjPduS6LUpm4OTlSdjW", "USER")));
        cosas.add(new Cosa(3, "cosa3", new User("admin", "$2a$12$L7Mg5yLrAtQJHemj/nvoqO7eechBNHP2TAYjPduS6LUpm4OTlSdjW", "ADMIN")));
        cosas.add(new Cosa(4, "cosa4", new User("user", "$2a$12$L7Mg5yLrAtQJHemj/nvoqO7eechBNHP2TAYjPduS6LUpm4OTlSdjW", "USER")));
    }

    public List<Cosa> getAllInfo() {
        return cosas;
    }

    public List<Cosa> getAll(String name) {
        List<Cosa> misCosas = cosas.stream().filter(c -> c.getEmpleado().getName().equals(name)).toList();
        if (misCosas.isEmpty())
            throw new NoTienesCosasException(Constantes.NO_TIENES_COSAS);
        return misCosas;
    }

    private Optional<Integer> findIndex(Cosa target) {
        return cosas.stream().filter(c -> c.getId() == target.getId()).findFirst().map(cosas::indexOf);
    }

    public Cosa update(Cosa cosa, User user) {
        Optional<Integer> indexOpt = findIndex(cosa);
        if (indexOpt.isPresent()) {
            int index = indexOpt.get();
            if (!cosas.get(index).getEmpleado().getName().equals(user.getName()))
                throw new NoEsTuCosaException(403,Constantes.NO_ES_TU_COSA);
            cosa.setEmpleado(user);
            cosas.set(index, cosa);
        } else {
            throw new NotFoundException(Constantes.COSA_NOT_FOUND);
        }
        return cosa;
}

public void delete(int id) {
    if (cosas.stream().noneMatch(c -> c.getId() == id))
        throw new NoEsTuCosaException(404, Constantes.NO_ES_TU_COSA);
    else
        cosas.removeIf(c -> c.getId() == id);
}
}
