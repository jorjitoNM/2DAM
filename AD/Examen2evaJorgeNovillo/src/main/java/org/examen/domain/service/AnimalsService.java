package org.examen.domain.service;

import jakarta.inject.Inject;
import org.examen.dao.repository.mysql.AnimalVisitsRepository;
import org.examen.dao.repository.mysql.AnimalsRepository;
import org.examen.dao.repository.mysql.VisitorsRepository;
import org.examen.domain.model.Visitor;

public class AnimalsService {

    private final AnimalsRepository animalsRepository;
    private final AnimalVisitsRepository animalVisitsRepository;
    private final VisitorsRepository visitorsRepository;

    @Inject
    public AnimalsService(AnimalsRepository animalsRepository, AnimalVisitsRepository animalVisitsRepository, VisitorsRepository visitorsRepository) {
        this.animalsRepository = animalsRepository;
        this.animalVisitsRepository = animalVisitsRepository;
        this.visitorsRepository = visitorsRepository;
    }

    public void visit(String habitatName, String visitorName) {
        Visitor visitor = visitorsRepository.get(visitorName);
        animalsRepository.getAllFromHabitat(habitatName).forEach(a ->
                        animalVisitsRepository.visit(visitor,a)
                );
    }

    public void delete(String animalName, boolean confirmation) {
        animalsRepository.delete(animalName,confirmation);
    }
}
