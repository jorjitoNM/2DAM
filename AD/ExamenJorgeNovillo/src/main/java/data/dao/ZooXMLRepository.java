package data.dao;

import configuration.Configuration;
import domain.errors.ErrorApp;
import domain.model.AnimalVisit;
import domain.model.AnimalVisits;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Log4j2
public class ZooXMLRepository {
    private final Configuration configuration;
    @Inject
    public ZooXMLRepository(Configuration configuration) {
        this.configuration = configuration;
    }

    public Either<ErrorApp, List<AnimalVisit>> loadXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(AnimalVisits.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            AnimalVisits xmlAnimalVisits = (AnimalVisits) unmarshaller.unmarshal(Files.newInputStream(configuration.getPathAnimalVisitsXML()));
            return Either.right(xmlAnimalVisits.getAnimalVisits());
        } catch (JAXBException | IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp("Ha habido un error cargando el XML"));
        }
    }
}
