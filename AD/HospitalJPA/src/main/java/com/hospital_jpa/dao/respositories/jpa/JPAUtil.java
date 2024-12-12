package com.hospital_jpa.dao.respositories.jpa;

import com.hospital_jpa.common.Constantes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private EntityManagerFactory emf;

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
