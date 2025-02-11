package data.remote;

import data.mappers.AnimalMapper;
import data.utilities.DBConnectionPool;
import data.utilities.SQLQueries;
import domain.errors.ErrorApp;
import domain.model.Animal;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class ZooJDBCAnimalRepository {
    private final DBConnectionPool pool;
    private final AnimalMapper animalMapper;

    @Inject
    public ZooJDBCAnimalRepository(DBConnectionPool pool, AnimalMapper animalMapper) {
        this.pool = pool;
        this.animalMapper = animalMapper;
    }

    public Either<ErrorApp, Animal> get(String animal) {
        try (Connection conn = pool.getConnection();
             PreparedStatement getAnimal = conn.prepareStatement(SQLQueries.GET_ANIMAL_BY_NAME)) {
            getAnimal.setString(1, animal);
            ResultSet resultSet = getAnimal.executeQuery();
            resultSet.next();
            return animalMapper.mapAnimal(resultSet);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }

    public Either<ErrorApp, Void> delete(String animalName, boolean extraInfo) {
        try (Connection conn = pool.getConnection();
        PreparedStatement deleteAnimal = conn.prepareStatement(SQLQueries.DELETE_ANIMAL);
        PreparedStatement deleteAnimalVisitors = conn.prepareStatement(SQLQueries.DELETE_ANIMAL_VISITORS)
        ) {
            conn.setAutoCommit(false);
            if (extraInfo) {
                deleteAnimalVisitors.setString(1,animalName);
                if (deleteAnimalVisitors.executeUpdate() < 1)
                    return Either.left(new ErrorApp("Could not delete this animal visit information"));
            }
            try {
                deleteAnimal.setString(1, animalName);
                if (deleteAnimal.executeUpdate() != 1)
                    return Either.left(new ErrorApp("Could not delete the animal"));
                else {
                    conn.commit();
                    return Either.right(null);
                }
            }catch (SQLIntegrityConstraintViolationException e) {
                conn.rollback();
                return Either.left(new ErrorApp("This animal has personal information"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }
}
