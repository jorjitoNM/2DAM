package org.examen.dao.repository.mysql;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import lombok.extern.log4j.Log4j2;
import org.examen.dao.utils.JPAUtil;
import org.examen.domain.model.Faction;
import org.examen.domain.model.Weapon;

import java.util.List;

@Log4j2
public class FactionsRepository {

    private final JPAUtil jpaUtil;

    @Inject
    public FactionsRepository(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    public boolean saveAll (List<Faction> factions) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = jpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            factions.forEach(em::persist);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error(e.getMessage(), e);
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public Faction get(String name) {
        Faction f = null;
        try (EntityManager em = jpaUtil.getEntityManager()) {
            f = em.createNamedQuery("GET_FACTION",Faction.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
        }
        return f;
    }

    public List<Faction> getAll() {
        List<Faction> factions;
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = jpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            factions = em.createNamedQuery("getAllFactions", Faction.class).getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return factions;
    }
}
