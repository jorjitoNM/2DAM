package data.remote;

import data.mappers.BattleMapper;
import data.remote.utilities.DBConnectionPool;
import data.remote.utilities.SQLQueries;
import domain.error.ErrorApp;
import domain.model.Battle;
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
    private final BattleMapper battleMapper;

    @Inject
    public FactionsJDBCRepository(DBConnectionPool pool, BattleMapper battleMapper) {
        this.pool = pool;
        this.battleMapper = battleMapper;
    }

    public Either<ErrorApp, Void> saveFactions(List<Faction> factions) {
        try (Connection conn = pool.getConnection();
             PreparedStatement insertFaction = conn.prepareStatement(SQLQueries.INSERT_FACTION);
             PreparedStatement insertWeapon = conn.prepareStatement(SQLQueries.INSERT_WEAPON);
             PreparedStatement insertWeaponFraction = conn.prepareStatement(SQLQueries.INSERT_WEAPON_FACTION);
        ) {
            for (Faction f : factions) {
                if (saveWeapons(insertWeapon, f.getWeapons())
                        .flatMap(weaponsPks -> addFaction(insertFaction, f)
                                .flatMap(faction -> {
                                    saveWeaponsFactions(insertWeaponFraction, weaponsPks, f.getName()).isRight();
                                    return null;
                                })).isLeft())
                    return Either.left(new ErrorApp(""));
            }
            if (insertFaction.executeBatch().length == factions.size())
                return Either.right(null);
            else
                return Either.left(new ErrorApp("Error al ejecutar las consultas en base de datos"));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }

    private Either<ErrorApp, Void> saveWeaponsFactions(PreparedStatement insertWeaponFraction, List<Integer> weaponsPks, String name) {
        try {
            insertWeaponFraction.setString(2, name);
            for (Integer weapon_id : weaponsPks) {
                insertWeaponFraction.setInt(1, weapon_id);
                if (!insertWeaponFraction.execute())
                    return Either.left(new ErrorApp("Failed to add the elements into weapon_factions table"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
        return Either.right(null);
    }


    private Either<ErrorApp, Faction> addFaction(PreparedStatement insertFaction, Faction f) {
        try {
            insertFaction.setString(1, f.getName());
            insertFaction.setString(2, f.getContact());
            insertFaction.setString(3, f.getPlanet());
            insertFaction.setInt(4, f.getNumberCS());
            insertFaction.setDate(5, Date.valueOf(f.getDateLastPurchase()));
            insertFaction.addBatch();
            return Either.right(null);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }

    private Either<ErrorApp, List<Integer>> saveWeapons(PreparedStatement insertWeapon, List<Weapon> weapons) throws SQLException {
        List<Integer> weaponsPks = new ArrayList<>();
        for (Weapon w : weapons) {
            try {
                insertWeapon.setString(1, w.getName());
                insertWeapon.setDouble(2, w.getPrice());
                ResultSet rs = insertWeapon.executeQuery();
                if (rs.next())
                    weaponsPks.add(rs.getInt(1));
                else
                    return Either.left(new ErrorApp("Failed to save the weapon into the data base"));
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                return Either.left(new ErrorApp("Failed to save the weapon into the data base"));
            }
        }
        return Either.right(weaponsPks);
    }

    public Either<ErrorApp, Integer> countRebelsWeapons(List<Weapon> weapons) {
        try (Connection conn = pool.getConnection();
             PreparedStatement insertWeapon = conn.prepareStatement(SQLQueries.INSERT_WEAPON);
             PreparedStatement deleteAllWeapons = conn.prepareStatement(SQLQueries.DELETE_ALL_WEAPONS);
        ) {
            for (Weapon w : weapons) {
                insertWeapon.setString(1, w.getName());
                insertWeapon.setInt(2, w.getPrice());
                if (insertWeapon.executeUpdate() != 1)
                    return Either.left(new ErrorApp("Failed to add the elements into weapons table"));
            }
            return Either.right(deleteAllWeapons.executeUpdate());
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }

    public Either<ErrorApp, Battle> saveBattle(Battle battle) {
        try (Connection conn = pool.getConnection();
             PreparedStatement insertBattle = conn.prepareStatement(SQLQueries.INSERT_BATTLE);
             PreparedStatement insertFaction = conn.prepareStatement(SQLQueries.INSERT_FACTION);
        ) {
            addFaction(insertFaction, battle.getFaction_one())
                    .flatMap(faction1 -> addFaction(insertFaction, battle.getFaction_two())
                    .flatMap(faction2 -> {
                        try {
                            insertBattle.setString(1, battle.getNombre());
                            insertFaction.setString(2, battle.getFaction_one().getName());
                            insertFaction.setString(3, battle.getFaction_two().getName());
                            insertFaction.setString(4, battle.getPlace());
                            insertFaction.setDate(5,Date.valueOf(battle.getDate().toString()));
                            return battleMapper.mapBattle(insertBattle.executeQuery(),faction1,faction2);
                        } catch (SQLException e) {
                            log.error(e.getMessage(), e);
                            return Either.left(new ErrorApp(e.getMessage()));
                        }
                    }));
        } catch (SQLException e) {
           log.error(e.getMessage(), e);
           return Either.left(new ErrorApp(e.getMessage()));
        }
        return Either.right(null);
    }

    public Either<ErrorApp, Integer> updateWeaponPrice(Weapon weapon,int newPrice ) {
        try (Connection conn = pool.getConnection();
        PreparedStatement updateWeaponPrice = conn.prepareStatement(SQLQueries.UPDATE_WEAPON_PRICE)
        ) {
            updateWeaponPrice.setInt(1,newPrice);
            updateWeaponPrice.setInt(2,weapon.getId());
            if (updateWeaponPrice.executeUpdate() == 1)
                return Either.right(newPrice);
            else
                return Either.left(new ErrorApp("Could not update the weapon price"));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorApp(e.getMessage()));
        }
    }
}
