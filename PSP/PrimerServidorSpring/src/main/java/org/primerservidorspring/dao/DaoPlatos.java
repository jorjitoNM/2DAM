package org.primerservidorspring.dao;


import net.datafaker.Faker;
import org.primerservidorspring.domain.model.Plato;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoPlatos {
    private static final List<Plato> platos  = new ArrayList<>();
    private final Faker faker;

    public DaoPlatos(Faker faker) {
        this.faker = faker;
        if (platos.isEmpty()) {
            int id = 0;
            while (id <= 10) {
                platos.add(new Plato(faker.food().dish(),fillIngredients(), id));
                id++;
            }
        }
    }

    public List<Plato> getDishes() {
        return platos;
    }

    public void deletePlato(int id) {
        platos.removeIf(p -> p.id()==id);
    }

    private List<String> fillIngredients () {
        int random = (int) (Math.random()*5+2);
        int i = 0;
        List<String> ingredients = new ArrayList<>();
        while (i < random) {
            ingredients.add(faker.food().ingredient());
            i++;
        }
        return ingredients;
    }

    public Plato updateDish(Plato p) {
        for (int i = 0; i < platos.size(); i++) {
            if (platos.get(i).id() == p.id()) {
                return platos.set(i, p);
            }
        }
        return null;
    }

    public Plato getDish(int i) {
        return platos.stream().filter(p -> p.id()==i).findFirst().orElse(null);
    }
}

