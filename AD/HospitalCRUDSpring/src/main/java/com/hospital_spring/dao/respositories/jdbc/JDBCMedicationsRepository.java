package com.hospital_spring.dao.respositories.jdbc;

import com.hospital_spring.dao.mappers.jdbc_mappers.MapMedications;
import com.hospital_spring.dao.model.MedicalRecord;
import com.hospital_spring.dao.model.Medication;
import com.hospital_spring.dao.respositories.MedicationsRepository;
import com.hospital_spring.dao.utilities.DBConnectionPool;
import com.hospital_spring.dao.utilities.SQLQueries;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Profile("jdbc")
@Repository
public class JDBCMedicationsRepository implements MedicationsRepository {

    private final DBConnectionPool pool;
    private final MapMedications medicationsMapper;

    public JDBCMedicationsRepository(DBConnectionPool pool, MapMedications medicationsMapper) {
        this.pool = pool;
        this.medicationsMapper = medicationsMapper;
    }

    public List<Medication> getPrescribedMedications (int medicalRecordId) {
        try (Connection conn = pool.getConnection();
            PreparedStatement getPrescribedMedications = conn.prepareStatement(SQLQueries.GET_PRESCRIBED_MEDICATIONS);
        ) {
            getPrescribedMedications.setInt(1, medicalRecordId);
            return medicationsMapper.readRS(getPrescribedMedications.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Medication> getAll() {
        try (Connection conn = pool.getConnection();
        Statement stmt = conn.createStatement();
        ) {
            return medicationsMapper.readRS(stmt.executeQuery(SQLQueries.GET_ALL_MEDICATIONS));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePatientMedications(int patientId) {

    }

    @Override
    public void deleteMedicalRecordMedications(int medicalRecordId) {

    }

    @Override
    public void save(MedicalRecord medicalRecord) {

    }

    @Override
    public void update(MedicalRecord medicalRecord) {

    }
}
