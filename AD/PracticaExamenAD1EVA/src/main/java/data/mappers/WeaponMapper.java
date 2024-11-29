package data.mappers;

import domain.error.ErrorApp;
import domain.model.Weapon;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class WeaponMapper {
    @Inject
    public WeaponMapper() {
    }
    public Either<ErrorApp, Weapon> mapWeapon (ResultSet rs) {
        try {
            return Either.right(new Weapon(
                    rs.getInt("id"),
                    rs.getString("wname"),
                    (int)rs.getDouble("wprice")
            ));
        } catch (SQLException e) {
            log.error(e.getMessage(),e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }
}
