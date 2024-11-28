package data.remote;

import data.remote.utilities.DBConnectionPool;
import data.remote.utilities.SQLQueries;
import domain.error.Error;
import domain.model.Faction;
import domain.model.Weapon;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class FactionsJDBCRepository {
    private final DBConnectionPool pool;

    @Inject
    public FactionsJDBCRepository(DBConnectionPool pool) {
        this.pool = pool;
    }

    public Either<Error, Void> saveFactions(List<Faction> factions) {
        try (Connection conn = pool.getConnection();
             PreparedStatement insertFaction = conn.prepareStatement(SQLQueries.INSERT_FACTIONS);
             PreparedStatement insertWeapon = conn.prepareStatement(SQLQueries.INSERT_WEAPON);
            PreparedStatement insertWeaponFraction = conn.prepareStatement(SQLQueries.INSERT_WEAPON_FACTION);
             ) {
            for (Faction f : factions) {
                if (saveWeapons(insertWeapon,f.getWeapons())
                        .flatMap(weaponsPks -> addFaction(insertFaction,f)
                                    .flatMap(nada -> {
                                        saveWeaponsFactions(insertWeaponFraction,weaponsPks,f.getName()).isRight();
                                        return null;
                                    })).isLeft())
                    return Either.left(new Error(""));
            }
            if (insertFaction.executeBatch().length == factions.size())
                return Either.right(null);
            else
                return Either.left(new Error("Error al ejecutar las consultas en base de datos"));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new Error(e.getMessage()));
        }
    }

    private Either<Error,Void> saveWeaponsFactions(PreparedStatement insertWeaponFraction, List<Integer> weaponsPks, String name) {
        try {
            insertWeaponFraction.setString(2,name);
        for (Integer weapon_id : weaponsPks) {
            insertWeaponFraction.setInt(1,weapon_id);
            if (!insertWeaponFraction.execute())
                return Either.left(new Error("Failed to add the elements into weapon_factions table"));
        }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new Error(e.getMessage()));
        }
        return Either.right(null);
    }


    private Either<Error,Void> addFaction(PreparedStatement insertFaction, Faction f) {
        try {
            insertFaction.setString(1, f.getName());
            insertFaction.setString(2, f.getContact());
            insertFaction.setString(3, f.getPlanet());
            insertFaction.setInt(4, f.getNumberCS());
            insertFaction.setDate(5, Date.valueOf(f.getDateLastPurchase()));
            insertFaction.addBatch();
            return Either.right(null);
        } catch (SQLException e) {
            log.error(e.getMessage(),e);
            return Either.left(new Error(e.getMessage()));
        }
    }

    private Either<Error,List<Integer>> saveWeapons(PreparedStatement insertWeapon, List<Weapon> weapons) throws SQLException {
       List<Integer> weaponsPks = new ArrayList<>();
       for (Weapon w : weapons) {
           try {
               insertWeapon.setString(1, w.getName());
               insertWeapon.setDouble(2, w.getPrice());
               ResultSet rs = insertWeapon.executeQuery();
               if (rs.next())
                   weaponsPks.add(rs.getInt(1));
               else
                   return Either.left(new Error("Failed to save the weapon into the data base"));
           } catch (SQLException e) {
               log.error(e.getMessage(),e);
               return Either.left(new Error("Failed to save the weapon into the data base"));
           }
       }
        return Either.right(weaponsPks);
    }
}
