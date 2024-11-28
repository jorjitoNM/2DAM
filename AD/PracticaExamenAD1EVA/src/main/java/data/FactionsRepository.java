package data;

import configuration.Configuration;
import domain.error.Error;
import domain.model.Faction;
import domain.model.Factions;
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
public class FactionsRepository {
    private final Configuration configuration;
    @Inject
    public FactionsRepository(Configuration configuration) {
        this.configuration = configuration;
    }

    public Either<Error,List<Faction>> loadXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(Factions.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Factions xmlFactions = (Factions) unmarshaller.unmarshal(Files.newInputStream(configuration.getPathFactionsXML()));
            return Either.right(xmlFactions.getFactions());
        } catch (JAXBException | IOException e) {
            log.error(e.getMessage(),e);
            return Either.left(new Error("Ha habido un error cargando el XML"));
        }
    }
}
