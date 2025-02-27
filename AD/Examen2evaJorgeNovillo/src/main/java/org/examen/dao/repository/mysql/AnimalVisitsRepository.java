package org.examen.dao.repository.mysql;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.log4j.Log4j2;
import org.examen.dao.utils.JPAUtil;
import org.examen.domain.errors.AppError;
import org.examen.domain.model.Animal;
import org.examen.domain.model.AnimalVisit;
import org.examen.domain.model.Visitor;

import java.time.LocalDate;

@Log4j2
public class AnimalVisitsRepository {
    private final JPAUtil jpaUtil;

    @Inject
    public AnimalVisitsRepository(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    public void visit (Visitor visitor, Animal animal) {
        EntityManager em = null;
        EntityTransaction tx = null;
        AnimalVisit animalVisit = new AnimalVisit(animal,visitor,LocalDate.now());
        try {
            em = jpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(animalVisit);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error(e.getMessage(), e);
            throw new AppError("No se ha podido visitar al animal deseado" + e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
