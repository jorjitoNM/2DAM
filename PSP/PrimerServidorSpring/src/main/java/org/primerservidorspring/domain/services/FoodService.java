package org.primerservidorspring.domain.services;


import org.primerservidorspring.dao.DaoPlatos;
import org.primerservidorspring.domain.model.Plato;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final DaoPlatos dao;

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
