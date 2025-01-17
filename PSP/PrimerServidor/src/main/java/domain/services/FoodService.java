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

    public void delete(String p) {
        dao.deletePlato(Integer.parseInt(p));
    }

    public Plato updateDish (Plato p) {
        return dao.updateDish(p);
    }

    public Plato getDish(String parameter) {
        return dao.getDish(Integer.parseInt(parameter));
    }
}
