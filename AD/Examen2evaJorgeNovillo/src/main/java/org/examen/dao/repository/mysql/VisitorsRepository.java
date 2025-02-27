package org.examen.dao.repository.mysql;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import lombok.extern.log4j.Log4j2;
import org.examen.dao.utils.JPAUtil;
import org.examen.domain.errors.AppError;
import org.examen.domain.model.Visitor;

import java.util.List;

@Log4j2
public class VisitorsRepository {
    private final JPAUtil jpaUtil;

    @Inject
    public VisitorsRepository(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    public Visitor get(String visitorName) {
        List<Visitor> visitorsList;
        try (EntityManager em = jpaUtil.getEntityManager()) {
            visitorsList = em.createNamedQuery("getVisitorByName", Visitor.class)
                    .setParameter("visitor_name", visitorName)
                    .getResultList();
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
            throw new AppError("No se ha encontrado el visitante"+e);
        }
        return visitorsList.getFirst();
    }
}
