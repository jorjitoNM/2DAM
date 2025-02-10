package org.springrest2.domain.services;



import org.springframework.stereotype.Service;
import org.springrest2.domain.model.Plato;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

//    private final DaoPlatos dao;
//
//    public FoodService(DaoPlatos dao) {
//        this.dao = dao;
//    }


    public List<Plato> getDishes () {
        //return dao.getDishes();
        return new ArrayList();
    }

    public boolean delete(Integer dishId) {
        //return dao.deletePlato(dishId);
        return true;
    }

    public Plato updateDish (Plato p) {
        //return dao.updateDish(p);
        return null;
    }

    public Plato getDish(Integer dishId) {
        //return dao.getDish(dishId);
        return null;
    }
}
