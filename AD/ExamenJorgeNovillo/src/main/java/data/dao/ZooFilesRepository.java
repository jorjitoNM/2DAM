package data.dao;

import configuration.Configuration;
import domain.errors.ErrorApp;
import domain.model.AnimalVisit;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardOpenOption.APPEND;

@Log4j2
public class ZooFilesRepository {
    private final Configuration configuration;

    @Inject
    public ZooFilesRepository(Configuration configuration) {
        this.configuration = configuration;
    }

    public Either<ErrorApp, Void> saveAnimalVisit(AnimalVisit animalVisit, String visitorName) {
        try (BufferedWriter bw = Files.newBufferedWriter(configuration.getPathAnimalVisitsTXT(), APPEND)) {
            bw.append(parseAnimalVisit(animalVisit, visitorName));
            return Either.right(null);
        } catch (IOException | RuntimeException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }

    private String parseAnimalVisit(AnimalVisit a, String visitorName) {
        return a.getAnimalId() + ";" + visitorName + ";" + a.getVisitDate().toString() + "\n";
    }
}
