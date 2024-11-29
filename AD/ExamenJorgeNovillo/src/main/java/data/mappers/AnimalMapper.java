package data.mappers;

import domain.errors.ErrorApp;
import domain.model.Animal;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class AnimalMapper {
    @Inject
    public AnimalMapper() {
    }
    public Either<ErrorApp, Animal> mapAnimal (ResultSet rs) {
        try {
            return Either.right(new Animal(
                    rs.getInt("Animal_ID"),
                    rs.getString("Name"),
                    rs.getString("Species"),
                    rs.getInt("Age"),
                    rs.getInt("Habitat_ID")
            ));
        } catch (SQLException e) {
            log.error(e.getMessage(),e);
            return Either.left(new ErrorApp("Could not map the provided animal"));
        }
    }
}
