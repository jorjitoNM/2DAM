package org.examen.dao.repository.mysql;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.log4j.Log4j2;
import org.examen.dao.utils.JPAUtil;
import org.examen.domain.model.Battle;
import org.examen.domain.model.Faction;
import org.examen.domain.model.Spy;

@Log4j2
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
            if (f1 == null) {
                f1 = new Faction(battle.getFactionOne().getName());
                em.persist(battle.getFactionOne());
            }
            Faction f2 = em.find(Faction.class, battle.getFactionOne().getName());
            if (f2 == null) {
                f2 = new Faction(battle.getFactionTwo().getName());
                em.persist(battle.getFactionOne());
            }
            battle.setFactionOne(f1);
            battle.setFactionTwo(f2);
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
