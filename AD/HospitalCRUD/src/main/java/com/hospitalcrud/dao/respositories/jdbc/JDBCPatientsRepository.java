package com.hospitalcrud.dao.respositories.jdbc;

import com.hospitalcrud.dao.mappers.jdbc_mappers.MapGetAllPatients;
import com.hospitalcrud.dao.model.Patient;
import com.hospitalcrud.dao.respositories.PatientRepository;
import com.hospitalcrud.dao.utilities.SQLQueries;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        return 0;
    }

    @Override
    public void update(Patient patient) {

    }

    @Override
    public boolean delete(int patientId, boolean confirmation) {
        return false;
    }
}
