package domain.service;

import data.dao.ZooFilesRepository;
import data.dao.ZooXMLRepository;
import data.remote.*;
import domain.errors.ErrorApp;
import domain.model.Animal;
import domain.model.AnimalVisit;
import domain.model.Visitor;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class ZooService {
    private final ZooJDBCAnimalRepository animalRepository;
    private final ZooJDBCAnimalVisitsRepository animalVisitsRepository;
    private final ZooJDBCVisitorRepository visitorRepository;
    private final ZooXMLRepository xmlRepository;
    private final ZooFilesRepository filesRepository;

    @Inject
    public ZooService(ZooJDBCAnimalRepository animalRepository, ZooJDBCAnimalVisitsRepository animalVisitsRepository, ZooJDBCVisitorRepository visitorRepository, ZooXMLRepository xmlRepository, ZooFilesRepository filesRepository) {
        this.animalRepository = animalRepository;
        this.animalVisitsRepository = animalVisitsRepository;
        this.visitorRepository = visitorRepository;
        this.xmlRepository = xmlRepository;
        this.filesRepository = filesRepository;
    }

    public Either<ErrorApp, List<AnimalVisit>> loadXML(String visitorName) {
        return visitorRepository.getVisitorId(visitorName)
                .flatMap(visitorId ->
                        xmlRepository.loadXML().flatMap(
                                visits -> animalVisitsRepository.save(visits.stream().filter(v -> v.getVisitorId() == visitorId).toList())));
    }

    public Either<ErrorApp, Void> saveInfo(Visitor visitor, List<String> animalVisits) {
        List<Animal> animales = new ArrayList<>();
        for (String animal : animalVisits) {
            Either<ErrorApp, Animal> either = animalRepository.get(animal);
            if (either.isRight())
                animales.add(either.get());
            else
                return Either.left(new ErrorApp(either.getLeft().message()));
        }
        return visitorRepository.saveVisitor(visitor)
                .flatMap(visitorId -> animalVisitsRepository.saveInfo(visitorId, animales));
    }

    public Either<ErrorApp, Void> deleteAnimal(String animalName, boolean extraInfo) {
        if (extraInfo) {
            return animalVisitsRepository.get(animalName)
                    .flatMap(animalVisits -> {
                        for (AnimalVisit av : animalVisits) {
                            animalVisitsRepository.getVisitorName(av)
                                    .flatMap(visitorName -> filesRepository.saveAnimalVisit(av, visitorName)
                                            .flatMap(nada -> animalRepository.delete(animalName, true)));
                        }
                        return Either.right(null);
                    });
        }
        return animalRepository.delete(animalName, false);
    }
}
