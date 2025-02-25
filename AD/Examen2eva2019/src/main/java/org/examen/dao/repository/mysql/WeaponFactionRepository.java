package org.examen.dao.repository.mysql;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.log4j.Log4j2;
import org.examen.dao.utils.JPAUtil;
import org.examen.domain.model.WeaponsFaction;

import java.util.List;

@Log4j2
public class WeaponFactionRepository {
    private final JPAUtil jpaUtil;

    @Inject
    public WeaponFactionRepository(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    public boolean saveAll (List<WeaponsFaction> weaponsFactions) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = jpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            weaponsFactions.forEach(em::persist);
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
}
