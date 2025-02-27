package org.examen.dao.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.examen.common.Constantes;

public class JPAUtil {
    private final EntityManagerFactory emf;

    public JPAUtil() {
        emf=getEmf();
    }


    private EntityManagerFactory getEmf() {
        return Persistence.createEntityManagerFactory(Constantes.PERSISTANCE_UNIT);
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
