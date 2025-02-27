package org.examen.domain.service;

import jakarta.inject.Inject;
import org.examen.dao.repository.mysql.FactionsRepository;
import org.examen.domain.model.Faction;

import java.util.List;

public class FactionsService {

    private final FactionsRepository repository;

    @Inject
    public FactionsService(FactionsRepository repository) {
        this.repository = repository;
    }

    public List<Faction> getAll() {
        return repository.getAll();
    }
}
