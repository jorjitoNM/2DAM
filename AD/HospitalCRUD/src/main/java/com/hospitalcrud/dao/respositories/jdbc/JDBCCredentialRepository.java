package com.hospitalcrud.dao.respositories.jdbc;

import com.hospitalcrud.dao.model.Credential;
import com.hospitalcrud.dao.model.Patient;
import com.hospitalcrud.dao.respositories.CredentialRepository;
import com.hospitalcrud.dao.utilities.SQLQueries;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
@Profile("inDevelopment")
public class JDBCCredentialRepository implements CredentialRepository {

    private final DBConnection dbConnection;

    public JDBCCredentialRepository(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Credential> getAll() {
        return List.of();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void save(Credential credential) {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement insertCredential = con.prepareStatement(SQLQueries.INSERT_CREDENTIAL)) {
            insertCredential.setString(1, credential.getUserName());
            insertCredential.setString(2, credential.getPassword());
            insertCredential.setInt(3, credential.getPatientId());
            insertCredential.setNull(4, 0);
            insertCredential.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Credential credential) {

    }

    @Override
    public boolean login(Credential credential) {
        String username = "";
        String password = "";
        try (Connection con = dbConnection.getConnection();
             PreparedStatement insertCredential = con.prepareStatement(SQLQueries.GET_CREDENTIAL)) {
            insertCredential.setString(1, credential.getUserName());
            insertCredential.setString(2, credential.getPassword());
            ResultSet rs = insertCredential.executeQuery();
            if (rs.next()) {
                 username = rs.getString("username");
                 password = rs.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return username.equals(credential.getUserName()) && password.equals(credential.getPassword());
    }
}
