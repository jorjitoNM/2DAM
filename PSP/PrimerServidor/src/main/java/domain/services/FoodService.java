package domain.services;

import dao.DaoPlatos;
import domain.model.Plato;
import jakarta.inject.Inject;

import java.util.List;

public class FoodService {

    private final DaoPlatos dao;

    @Inject
    public FoodService(DaoPlatos dao) {
        this.dao = dao;
    }


    public List<Plato> getDishes () {
        return dao.getDishes();
    }
}
