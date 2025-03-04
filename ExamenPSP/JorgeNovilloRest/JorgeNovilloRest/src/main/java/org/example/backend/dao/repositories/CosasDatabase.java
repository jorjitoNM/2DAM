package org.example.backend.dao.repositories;

import org.example.backend.common.Constantes;
import org.example.backend.domain.errors.NoTienesCosasException;
import org.example.backend.domain.model.Cosa;
import org.example.backend.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CosasDatabase {

    private final List<Cosa> cosas = new ArrayList<>();

    public CosasDatabase () {
        cosas.add(new Cosa("cosa1",new User("admin", "$2a$12$L7Mg5yLrAtQJHemj/nvoqO7eechBNHP2TAYjPduS6LUpm4OTlSdjW", Constantes.ADMIN_ROLE)));
        cosas.add(new Cosa("cosa2",new User("user", "$2a$12$L7Mg5yLrAtQJHemj/nvoqO7eechBNHP2TAYjPduS6LUpm4OTlSdjW", Constantes.USER_ROLE)));
        cosas.add(new Cosa("cosa3",new User("admin", "$2a$12$L7Mg5yLrAtQJHemj/nvoqO7eechBNHP2TAYjPduS6LUpm4OTlSdjW", Constantes.ADMIN_ROLE)));
        cosas.add(new Cosa("cosa4",new User("user", "$2a$12$L7Mg5yLrAtQJHemj/nvoqO7eechBNHP2TAYjPduS6LUpm4OTlSdjW", Constantes.USER_ROLE)));
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

    public Cosa update(Cosa cosa, User user) {
        cosas.stream().filter(c -> c.getName().equals(cosa.getName()));
        return cosa;
    }
}
