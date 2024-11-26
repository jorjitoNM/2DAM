package data.utilities;

import configuration.Configuration;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    private final Configuration config;

    @Inject
    public DBConnection(Configuration config) {
        this.config = config;
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(config.getDbUrl(), config.getUser_name(), config.getPassword());
    }
}
