package com.hospitalcrud.dao.respositories.jdbc;

import com.hospitalcrud.dao.mappers.jdbc_mappers.MapMedicalRecords;
import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.respositories.MedicalRecordsRepository;
import com.hospitalcrud.dao.utilities.DBConnectionPool;
import com.hospitalcrud.dao.utilities.SQLQueries;
import com.hospitalcrud.domain.error.FOREIGN_KEY_ERROR;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
        try (Connection con = pool.getConnection();
            PreparedStatement getMedicalRecords = con.prepareStatement(SQLQueries.GET_MEDICAL_RECORDS)) {
            getMedicalRecords.setInt(1, idPatient);
            return medicalRecordsMapper.readRS(getMedicalRecords.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int medicalRecordId) {
        try (Connection conn = pool.getConnection();
            PreparedStatement deleteMedicalRecord = conn.prepareStatement(SQLQueries.DELETE_MEDICAL_RECORD);
            PreparedStatement deletePrescribedMedications = conn.prepareStatement(SQLQueries.DELETE_PRESCRIBED_MEDICATIONS)) {
            conn.setAutoCommit(false);
            deletePrescribedMedications.setInt(1, medicalRecordId);
            if (deletePrescribedMedications.executeUpdate() == 1) {
                deleteMedicalRecord.setInt(1,medicalRecordId);
                if (deletePrescribedMedications.executeUpdate() == 1)
                    conn.commit();
                else
                    conn.rollback();
            }
            else
                throw new FOREIGN_KEY_ERROR();
        } catch (SQLException e) {
            throw new FOREIGN_KEY_ERROR();
        }
    }

    @Override
    public int save(MedicalRecord medicalRecord) {
        try (Connection conn = pool.getConnection();
            PreparedStatement addMedicalRecord = conn.prepareStatement(SQLQueries.INSERT_MEDICAL_RECORD, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement addPrescribedMedications = conn.prepareStatement(SQLQueries.ADD_PRESCRIBED_MEDICATIONS)) {
            conn.setAutoCommit(false);
            addMedicalRecord.setInt(1,medicalRecord.getIdPatient());
            addMedicalRecord.setInt(2,medicalRecord.getIdDoctor());
            addMedicalRecord.setString(3,medicalRecord.getDiagnosis());
            addMedicalRecord.setDate(4, Date.valueOf(medicalRecord.getDate().toString()));
            addMedicalRecord.executeUpdate();
            ResultSet rs = addMedicalRecord.getGeneratedKeys();
            int medicalRecordId;
            AtomicInteger i = new AtomicInteger();
            if (rs.next()) {
                medicalRecordId = rs.getInt(1);
                medicalRecord.getMedications().forEach(m -> {
                    try {
                        addPrescribedMedications.setInt(1, medicalRecordId);
                        addPrescribedMedications.setString(2, medicalRecord.getMedications().get(i.get()).getMedicationName());
                        addPrescribedMedications.setString(3,medicalRecord.getMedications().get(i.get()).getDosage());
                        i.addAndGet(1);
                        addPrescribedMedications.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                conn.commit();
            }
            else {
                medicalRecordId = -1;
                conn.rollback();
            }
            return medicalRecordId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
