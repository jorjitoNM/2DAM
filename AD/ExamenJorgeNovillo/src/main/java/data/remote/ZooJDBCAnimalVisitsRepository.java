package data.remote;

import data.mappers.AnimalVisitMapper;
import data.utilities.DBConnectionPool;
import data.utilities.SQLQueries;
import domain.errors.ErrorApp;
import domain.model.Animal;
import domain.model.AnimalVisit;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ZooJDBCAnimalVisitsRepository {
    private final DBConnectionPool pool;
    private final AnimalVisitMapper animalVisitMapper;

    @Inject
    public ZooJDBCAnimalVisitsRepository(DBConnectionPool pool, AnimalVisitMapper animalVisitMapper) {
        this.pool = pool;
        this.animalVisitMapper = animalVisitMapper;
    }

    public Either<ErrorApp, List<AnimalVisit>> save(List<AnimalVisit> list) {
        try (Connection conn = pool.getConnection();
             PreparedStatement insertAnimalVisit = conn.prepareStatement(SQLQueries.INSERT_ANIMAL_VISIT);
        ) {
            for (AnimalVisit av : list) {
                insertAnimalVisit.setInt(1, av.getAnimalId());
                insertAnimalVisit.setInt(2, av.getVisitorId());
                insertAnimalVisit.setDate(3, Date.valueOf(av.getVisitDate().toString()));
                if (insertAnimalVisit.executeUpdate() != 1)
                    return Either.left(new ErrorApp("Could not insert animal visit"));
            }
            return Either.right(list);
        } catch (SQLIntegrityConstraintViolationException e) {
            return Either.left(new ErrorApp("The visit was already in the database"));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }

    public Either<ErrorApp, List<AnimalVisit>> get(String animal) {
        try (Connection conn = pool.getConnection();
             PreparedStatement getAnimalVisits = conn.prepareStatement(SQLQueries.GET_ANIMAL_VISITS)) {
            List<AnimalVisit> animalVisits = new ArrayList<>();
            getAnimalVisits.setString(1, animal);
            ResultSet resultSet = getAnimalVisits.executeQuery();
            while (resultSet.next()) {
                Either<ErrorApp, AnimalVisit> either = animalVisitMapper.mapAnimalVisit(resultSet);
                if (either.isRight())
                    animalVisits.add(either.get());
                else
                    return Either.left(new ErrorApp(either.getLeft().message()));
            }
            return Either.right(animalVisits);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }

    public Either<ErrorApp, Void> saveInfo(int visitorId, List<Animal> visits) {
        try (Connection conn = pool.getConnection();
             PreparedStatement insertAnimalVisit = conn.prepareStatement(SQLQueries.INSERT_ANIMAL_VISIT)
        ) {
            insertAnimalVisit.setInt(2, visitorId);
            for (Animal a : visits) {
                insertAnimalVisit.setInt(1, a.getId());
                insertAnimalVisit.setDate(3, Date.valueOf(LocalDate.now().toString()));
                if (insertAnimalVisit.executeUpdate() != 1)
                    return Either.left(new ErrorApp("Could not save the animal visit"));
            }
            return Either.right(null);
        } catch (SQLIntegrityConstraintViolationException e) {
            return Either.left(new ErrorApp("The visit was already in the database"));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }

    public Either<ErrorApp, String> getVisitorName(AnimalVisit av) {
        try (Connection conn = pool.getConnection();
        PreparedStatement getVisitorName = conn.prepareStatement(SQLQueries.GET_VISITOR_NAME)) {
            getVisitorName.setInt(1,av.getVisitorId());
            ResultSet resultSet = getVisitorName.executeQuery();
            resultSet.next();
            return Either.right(resultSet.getString(1));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }
}
