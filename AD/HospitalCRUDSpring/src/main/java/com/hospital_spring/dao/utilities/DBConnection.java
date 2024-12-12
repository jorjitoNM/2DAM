package com.hospital_spring.dao.utilities;

import com.hospital_spring.dao.configuration.XMLConfiguration;
import org.springframework.stereotype.Component;

import java.sql.*;

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
