package domain.services;

import dao.DaoPlatos;
import domain.model.Plato;

import java.util.List;

public class FoodService {

    private final DaoPlatos dao;

    public FoodService() {
        dao = new DaoPlatos();
    }

    public List<Plato> getDishes () {
        return dao.getDishes();
    }
}
