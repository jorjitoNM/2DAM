package data.remote.utilities;

public class SQLQueries {

    public static final String INSERT_FACTION = "insert into faction (fname,contact,planet,number_controlled_systems,date_last_purchase) values (?,?,?,?,?) ";
    public static final String INSERT_WEAPON = "insert into weapons (wname,wprice) values (?,?)";
    public static final String INSERT_WEAPON_FACTION = "insert into weapon_faction (name_faction,id_weapon) values (?,?)";
    public static final String DELETE_ALL_WEAPONS = "delete from weapons";
    public static final String INSERT_BATTLE = "insert into battles (bname,faction_one,faction_two,bplace,bdate,id_spy) ?,?,?,?,?,?)";
    public static final String UPDATE_WEAPON_PRICE = "update from weapons set wprice = ? where id = ?";
    public static final String GET_ALL_FACTIONS = "select * from faction";
    public static final String GET_ALL_FACTION_WEAPONS = "select w.id,w.wname,w.wprice from weapons w join weapons_factions wf on w.id = wf.id_weapon where wf.name_faction = ?";
    public static final String INSERT_WEAPON_WF = "insert into weapons_factions values (?,?,?)";

    private SQLQueries() {
    }
}
