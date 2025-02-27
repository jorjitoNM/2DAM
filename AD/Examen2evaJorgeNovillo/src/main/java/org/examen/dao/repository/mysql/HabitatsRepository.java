package org.examen.dao.repository.mysql;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import lombok.extern.log4j.Log4j2;
import org.examen.dao.utils.JPAUtil;
import org.examen.domain.model.Habitat;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class HabitatsRepository {
    private final JPAUtil jpaUtil;

    @Inject
    public HabitatsRepository(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    public Habitat get (String name) {
        Habitat habitat = null;
        List<Habitat> habitatList = new ArrayList<>();
        try (EntityManager em = jpaUtil.getEntityManager()) {
            habitatList = em.createNamedQuery("getHabitat",Habitat.class)
                    .setParameter("name", name)
                    .getResultList();
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
        }
        return habitatList.getFirst();
    }
}
