package data.mappers;

import domain.errors.ErrorApp;
import domain.model.AnimalVisit;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Log4j2
public class AnimalVisitMapper {
    @Inject
    public AnimalVisitMapper() {}
    public Either<ErrorApp, AnimalVisit> mapAnimalVisit(ResultSet rs) {
        try {
            return Either.right(new AnimalVisit(
                    rs.getInt("Animal_ID"),
                    rs.getInt("Visitor_ID"),
                    LocalDate.parse(rs.getDate("Visit_Date").toString())
            ));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp("Could not map the animal visit"));
        }
    }
}
