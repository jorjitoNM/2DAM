package org.springrest.dao;


import net.datafaker.Faker;
import org.springframework.stereotype.Repository;
import org.springrest.domain.model.Plato;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class DaoPlatos {
    private static final List<Plato> platos  = new ArrayList<>();
    private final Faker faker;

    public DaoPlatos(Faker faker) {
        this.faker = faker;
        int id = 0;
        while (id < 10) {
            platos.add(new Plato(faker.food().dish(),fillIngredients(), id));
            id++;
        }
    }

    public List<Plato> getDishes() {
        return platos;
    }

    public boolean deletePlato(int id) {
        return platos.removeIf(p -> p.id()==id);
    }

    private List<String> fillIngredients() {
        Random r = new Random();
        int numberOfIngredients = r.nextInt(2,5);
        List<String> ingredients = new ArrayList<>();
        for (int i = 0; i < numberOfIngredients; i++) {
            ingredients.add(faker.food().ingredient());
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

