package org.example.hospitaljpa.dao.respositories.jdbc;

import com.hospitalcrud.dao.model.Credential;
import com.hospitalcrud.dao.model.Patient;
import com.hospitalcrud.dao.respositories.CredentialRepository;
import com.hospitalcrud.dao.utilities.DBConnection;
import com.hospitalcrud.dao.utilities.SQLQueries;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Profile("jdbc")
@Log4j2
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
    public boolean delete(int patientId) {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.DELETE_CREDENTIAL)) {
            preparedStatement.setInt(1, patientId);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    @Override
    public void save(Patient patient) {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement insertCredential = con.prepareStatement(SQLQueries.INSERT_CREDENTIAL)) {
            insertCredential.setString(1, patient.getCredential().getUserName());
            insertCredential.setString(2, patient.getCredential().getPassword());
            insertCredential.setInt(3, patient.getId());
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
    public Credential get(String username) {
        Credential credential = null;
        try (Connection con = dbConnection.getConnection();
             PreparedStatement insertCredential = con.prepareStatement(SQLQueries.GET_CREDENTIAL)) {
            insertCredential.setString(1, username);
            ResultSet rs = insertCredential.executeQuery();
            if (rs.next()) {
                 credential = new Credential(
                         rs.getString("username"),
                         rs.getString("password"),
                         rs.getInt("patient_id"),
                         rs.getInt("doctor_id"));
            }
            return credential;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
