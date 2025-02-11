package data.mappers;

import domain.error.ErrorApp;
import domain.model.Battle;
import domain.model.Faction;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Log4j2
public class BattleMapper {
    @Inject
    public BattleMapper() {
    }

    public Either<ErrorApp, Battle> mapBattle(ResultSet rs, Faction f1, Faction f2) {
        try {
            if (rs.next())
                return Either.right(new Battle(
                        rs.getInt("id"),
                        rs.getString("bname"),
                        f1,
                        f2,
                        rs.getString("bplace"),
                        LocalDate.parse(rs.getDate("bdate").toString()),
                        rs.getInt("id_spy")
                ));
            else
                return Either.left(new ErrorApp("Could not map the battle db objct into a local battle object"));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }
}
