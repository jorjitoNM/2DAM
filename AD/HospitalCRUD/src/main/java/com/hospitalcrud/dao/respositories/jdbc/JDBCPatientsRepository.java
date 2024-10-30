package com.hospitalcrud.dao.respositories.jdbc;

import com.hospitalcrud.dao.mappers.jdbc_mappers.MapGetAllPatients;
import com.hospitalcrud.dao.model.Patient;
import com.hospitalcrud.dao.respositories.PatientRepository;
import com.hospitalcrud.dao.utilities.SQLQueries;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Profile("inDevelopment")
@Repository
public class JDBCPatientsRepository implements PatientRepository {
    private final DBConnection dbConnection;
    private final MapGetAllPatients patientsMapper;

    public JDBCPatientsRepository(DBConnection dbConnection, MapGetAllPatients patientsMapper) {
        this.dbConnection = dbConnection;
        this.patientsMapper = patientsMapper;
    }


    @Override
    public List<Patient> getAll() {
        try (Connection con = dbConnection.getConnection();
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet resultSet = statement.executeQuery(SQLQueries.GET_ALL_PATIENTS);
            return patientsMapper.readRS(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(Patient patient) {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement insertPatient = con.prepareStatement(SQLQueries.INSERT_PATIENT,Statement.RETURN_GENERATED_KEYS)) {
            setPatientValues(patient, insertPatient).executeUpdate();
            ResultSet rs = insertPatient.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            } else
                return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Patient patient) {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.UPDATE_PATIENT)) {
            preparedStatement.setInt(4,patient.getId());
            setPatientValues(patient,preparedStatement).executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    @Override
    public boolean delete(int patientId, boolean confirmation) {
        try (Connection con = dbConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.DELETE_PATIENT)) {
            preparedStatement.setInt(1, patientId);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    private PreparedStatement setPatientValues (Patient patient,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, patient.getName());
        preparedStatement.setDate(2,Date.valueOf(patient.getBirthDate()));
        preparedStatement.setString(3,patient.getPhone());
        return preparedStatement;
    }
}
