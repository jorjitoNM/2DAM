package data.mappers;

import domain.error.ErrorApp;
import domain.model.Faction;
import domain.model.Weapon;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class FactionMapper {
    @Inject
    public FactionMapper() {
    }

    public Either<ErrorApp, Faction> mapFaction(ResultSet rs, List<Weapon> weapons) {
        try {
            rs.next();
            return Either.right(new Faction(
                    rs.getString("fname"),
                    rs.getString("contact"),
                    rs.getString("planet"),
                    rs.getInt("number_controlled_systems"),
                    LocalDate.parse(rs.getDate("date_last_purchase").toString()),
                    weapons
            ));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }
}
