package org.examen.dao.repository.mysql;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.examen.dao.utils.JPAUtil;
import org.examen.domain.model.Battle;
import org.examen.domain.model.Faction;

public class BattlesRepository {
    private final JPAUtil jpaUtil;

    @Inject
    public BattlesRepository(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    public void save(Battle battle) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = jpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Faction f1 = em.find(Faction.class, battle.getFactionOne().getName());
            if (f1 == null)
                em.persist(battle.getFactionOne());
            Faction f2 = em.find(Faction.class, battle.getFactionOne().getName());
            if (f2 == null)
                em.persist(battle.getFactionOne());
            em.persist(battle);
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
    }
}
