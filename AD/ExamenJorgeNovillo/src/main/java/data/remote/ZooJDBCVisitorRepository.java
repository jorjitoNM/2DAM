package data.remote;

import data.utilities.DBConnectionPool;
import data.utilities.SQLQueries;
import domain.errors.ErrorApp;
import domain.model.Visitor;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class ZooJDBCVisitorRepository {
    private final DBConnectionPool pool;
    @Inject
    public ZooJDBCVisitorRepository(DBConnectionPool pool) {
        this.pool = pool;
    }

    public Either<ErrorApp, Integer> getVisitorId(String visitorName) {
        try (Connection conn = pool.getConnection();
             PreparedStatement getVisitorId = conn.prepareStatement(SQLQueries.GET_VISITOR_ID)) {
            getVisitorId.setString(1, visitorName);
            ResultSet resultSet = getVisitorId.executeQuery();
            resultSet.next();
            return Either.right(resultSet.getInt(1));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }


    public Either<ErrorApp, Integer> saveVisitor(Visitor visitor) {
        try (Connection conn = pool.getConnection();
                PreparedStatement insertVisitor = conn.prepareStatement(SQLQueries.INSERT_VISITOR, Statement.RETURN_GENERATED_KEYS);
        ) {
            insertVisitor.setString(1, visitor.getNombre());
            insertVisitor.setString(2, visitor.getEmail());
            insertVisitor.setInt(3,visitor.getTickets());
            insertVisitor.executeUpdate();
            ResultSet resultSet = insertVisitor.getGeneratedKeys();
            resultSet.next();
            return Either.right(resultSet.getInt(1));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }
}
