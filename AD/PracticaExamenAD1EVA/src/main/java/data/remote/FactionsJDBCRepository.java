package data.remote;

import data.remote.utilities.DBConnectionPool;
import data.remote.utilities.SQLQueries;
import domain.error.Error;
import domain.model.Faction;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.List;

@Log4j2
public class FactionsJDBCRepository {
    private final DBConnectionPool pool;
    @Inject
    public FactionsJDBCRepository(DBConnectionPool pool) {
        this.pool = pool;
    }

    public Either<Error,Void> saveFactions(List<Faction> factions) {
        try (Connection conn = pool.getConnection();
             PreparedStatement insertFaction = conn.prepareStatement(SQLQueries.INSERT_FACTIONS);
        ) {
            for (Faction f : factions) {
                insertFaction.setString(1, f.getContact());
                insertFaction.setString(2, f.getPlanet());
                insertFaction.setInt(3,f.getNumberCS());
                insertFaction.setDate(4, Date.valueOf(f.getDateLastPurchase()));
                insertFaction.addBatch();
            }
            if (insertFaction.executeBatch().length == factions.size())
                return Either.right(null);
            else
                return Either.left(new Error("Error al ejecutar las consultas en base de datos"));
        } catch (SQLException e) {
            log.error(e.getMessage(),e);
            return Either.left(new Error(e.getMessage()));
        }
    }
}
