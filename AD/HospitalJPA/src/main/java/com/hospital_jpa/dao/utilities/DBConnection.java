package com.hospital_jpa.dao.utilities;

import com.hospital_jpa.dao.configuration.XMLConfiguration;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBConnection {

    private XMLConfiguration config;

    public DBConnection() {
        this.config = XMLConfiguration.getInstance();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(config.getDbUrl(), config.getUser_name(), config.getPassword());
    }
}
