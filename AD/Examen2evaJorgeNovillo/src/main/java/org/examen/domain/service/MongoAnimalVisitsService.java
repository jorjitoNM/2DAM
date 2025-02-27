package org.examen.domain.service;

import jakarta.inject.Inject;
import org.examen.dao.repository.mongo.MongoAnimalVisitsRepository;

import java.time.LocalDate;

public class MongoAnimalVisitsService {
    private final MongoAnimalVisitsRepository mongoAnimalVisitsRepository;

    @Inject
    public MongoAnimalVisitsService(MongoAnimalVisitsRepository repository) {
        this.mongoAnimalVisitsRepository = repository;
    }

    public void update(String visitorName, String animalName, LocalDate date) {
        mongoAnimalVisitsRepository.update(visitorName,animalName,date);
    }

}
