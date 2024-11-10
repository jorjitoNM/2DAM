package com.hospitalcrud.dao.respositories.jdbc;

import com.hospitalcrud.dao.mappers.jdbc_mappers.MapMedicalRecords;
import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.respositories.MedicalRecordsRepository;
import com.hospitalcrud.dao.utilities.DBConnectionPool;
import com.hospitalcrud.dao.utilities.SQLQueries;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
@Profile("inDevelopment")
public class JDBCMedicalRecordsRepository implements MedicalRecordsRepository {


    private final MapMedicalRecords medicalRecordsMapper;
    private final DBConnectionPool pool;

    public JDBCMedicalRecordsRepository(MapMedicalRecords medicalRecordsMapper, DBConnectionPool pool) {
        this.medicalRecordsMapper = medicalRecordsMapper;
        this.pool = pool;
    }

    @Override
    public List<MedicalRecord> getAll(int idPatient) {
        try (Connection con = pool.getConnection()) {
            PreparedStatement getMedicalRecords = con.prepareStatement(SQLQueries.GET_MEDICAL_RECORDS);
            getMedicalRecords.setInt(1, idPatient);
            return medicalRecordsMapper.readRS(getMedicalRecords.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int save(MedicalRecord medicalRecord) {
        return 0;
    }

    @Override
    public List<MedicalRecord> update(MedicalRecord medicalRecord) {
        return List.of();
    }

    @Override
    public void saveMedicalRecords(List<MedicalRecord> medicalRecords) {

    }

    @Override
    public void deletePatientMedicalRecords(int patientId) {

    }
}
