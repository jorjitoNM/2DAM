package data.remote.utilities;

public class SQLQueries {

    public static final String INSERT_FACTIONS = "insert into faction values (contact,planet,number_controlled_systems,date_last_purchase) values ?,?,? ";

    private SQLQueries() {
    }
}
