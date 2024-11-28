package domain.service;

import data.FactionsRepository;
import data.remote.FactionsJDBCRepository;
import domain.error.Error;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class Service {
    private final FactionsRepository factionsRepository;
    private final FactionsJDBCRepository factionsJDBCRepository;
    @Inject
    public Service(FactionsRepository factionsRepository, data.remote.FactionsJDBCRepository factionsJDBCRepository) {
        this.factionsRepository = factionsRepository;
        this.factionsJDBCRepository = factionsJDBCRepository;
    }

    public Either<Error,Void> loadXml () {
        return factionsRepository.loadXML()
                .flatMap(factionsJDBCRepository::saveFactions);
    }
}