package dao;

import domain.model.Plato;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class DaoPlatos {
    private final List<Plato> platos;
    private final Faker faker;

    public DaoPlatos() {
        platos = new ArrayList<>();
        faker = new Faker();
        int id = 0;
        while (id <= 10) {
            platos.add(new Plato(faker.food().dish(),fillIngredients(), id));
            id++;
        }
    }

    public List<Plato> getDishes() {
        return platos;
    }

    public void deletePlato(int id) {
        platos.remove(id);
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
}
