package data.remote.utilities;

public class SQLQueries {

    public static final String INSERT_FACTIONS = "insert into faction (fname,contact,planet,number_controlled_systems,date_last_purchase) values (?,?,?,?,?) ";
    public static final String INSERT_WEAPON = "insert into weapons (wname,wprice) values (?,?)";
    public static final String INSERT_WEAPON_FACTION = "insert into weapon_faction (name_faction,id_weapon) values (?,?)";

    private SQLQueries() {
    }
}
