package org.examen.dao.repository.mysql;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.RollbackException;
import lombok.extern.log4j.Log4j2;
import org.examen.dao.repository.mongo.MongoAnimalVisitsRepository;
import org.examen.dao.utils.JPAUtil;
import org.examen.domain.errors.AnimalHashVisits;
import org.examen.domain.errors.AppError;
import org.examen.domain.model.Animal;
import org.examen.domain.model.AnimalVisit;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class AnimalsRepository {
    private final JPAUtil jpaUtil;
    private final MongoAnimalVisitsRepository mongoAnimalVisitsRepository;

    @Inject
    public AnimalsRepository(JPAUtil jpaUtil, MongoAnimalVisitsRepository mongoAnimalVisitsRepository) {
        this.jpaUtil = jpaUtil;
        this.mongoAnimalVisitsRepository = mongoAnimalVisitsRepository;
    }

    public List<Animal> getAllFromHabitat (String habitatName) {
        List<Animal> animals;
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = jpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            animals = em.createNamedQuery("getAllAnimalsFromHabitat", Animal.class)
                    .setParameter("habitat_name",habitatName)
                    .getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error(e.getMessage(), e);
            throw new AppError("No se ha podido encontrar a los animales del habitat especificado"+e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return animals;
    }

    public void delete (String animalName, boolean confirmation) {
        EntityTransaction tx = null;
        try (EntityManager em = jpaUtil.getEntityManager()) {
            Animal animal = get(animalName);
            tx = em.getTransaction();
            tx.begin();
            if (confirmation) {
                List<AnimalVisit> visits = em.createQuery(
                                "SELECT av FROM AnimalVisit av WHERE av.animal.name = :animal_name", AnimalVisit.class)
                        .setParameter("animal_name", animalName)
                        .getResultList();
                mongoAnimalVisitsRepository.save(visits);
                for (AnimalVisit av : visits) {
                    em.remove(av);
                }
            }
            em.remove(em.find(Animal.class, animal.getId()));
            tx.commit();
        } catch (RollbackException e) {
            throw new AnimalHashVisits("No se ha podido eliminar al animal porque tiene visitas");
        } catch (Exception e) {
            assert tx != null;
            if (tx.isActive()) tx.rollback();
            log.error(e.getMessage(), e);
        }
    }

    public Animal get (String animalName) {
        List<Animal> animals = new ArrayList<>();
        try (EntityManager em = jpaUtil.getEntityManager()) {
            animals = em.createNamedQuery("getAnimalByName",Animal.class)
                    .setParameter("animal_name", animalName)
                    .getResultList();
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
        }
        return animals.getFirst();
    }
}
