package org.examen.dao.repository.mysql;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import lombok.extern.log4j.Log4j2;
import org.examen.dao.utils.JPAUtil;
import org.examen.domain.model.Weapon;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class WeaponsRepository {
    private final JPAUtil jpaUtil;

    @Inject
    public WeaponsRepository(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    public boolean saveAll (List<Weapon> weapons) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = jpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            weapons.forEach(em::persist);
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

    public void save(Weapon weapon) {
        EntityManager em = null;
        EntityTransaction tx = null;
        weapon.getWeaponsFactions().forEach(f -> f.setWeapon(weapon));
        try {
            em = jpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(weapon);
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

    public void update(Weapon weapon) {
        EntityManager em = null;
        EntityTransaction tx = null;
        weapon.getWeaponsFactions().forEach(f -> f.setWeapon(weapon));
        try {
            em = jpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(weapon);
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

    public List<Weapon> getAll() {
        List<Weapon> weapons;
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = jpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            weapons = em.createNamedQuery("getAllWeapons", Weapon.class).getResultList();
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
        return weapons;
    }

    public List<Weapon> getAll(String factionName) {
        List<Weapon> weapons;
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = jpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            weapons = em.createNamedQuery("getAllFactionWeapons", Weapon.class)
                    .setParameter("faction_name",factionName)
                    .getResultList();
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
        return weapons;
    }
}
