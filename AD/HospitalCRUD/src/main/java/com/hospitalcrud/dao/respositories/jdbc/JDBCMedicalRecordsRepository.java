package com.hospitalcrud.dao.respositories.jdbc;

import com.hospitalcrud.dao.mappers.jdbc_mappers.MapMedicalRecords;
import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.respositories.MedicalRecordsRepository;
import com.hospitalcrud.dao.utilities.DBConnectionPool;
import com.hospitalcrud.dao.utilities.SQLQueries;
import com.hospitalcrud.domain.error.FOREIGN_KEY_ERROR;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
@Profile("jdbc")
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
                deleteMedicalRecord.setInt(1, medicalRecordId);
                if (deleteMedicalRecord.executeUpdate() == 1)
                    conn.commit();
                else
                    conn.rollback();
            } else {
                conn.rollback();
                throw new FOREIGN_KEY_ERROR();
            }
        } catch (SQLException e) {
            throw new FOREIGN_KEY_ERROR();
        }
    }

    @Override
    public int save(MedicalRecord medicalRecord) {
        try (Connection conn = pool.getConnection();
             PreparedStatement addMedicalRecord = conn.prepareStatement(SQLQueries.INSERT_MEDICAL_RECORD, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement addPrescribedMedications = conn.prepareStatement(SQLQueries.ADD_PRESCRIBED_MEDICATIONS);
        ) {
            conn.setAutoCommit(false);
            addMedicalRecord.setInt(1, medicalRecord.getIdPatient());
            addMedicalRecord.setInt(2, medicalRecord.getIdDoctor());
            addMedicalRecord.setString(3, medicalRecord.getDiagnosis());
            addMedicalRecord.setDate(4, Date.valueOf(medicalRecord.getDate().toString()));
            try {
                addMedicalRecord.executeUpdate();
                ResultSet rs = addMedicalRecord.getGeneratedKeys();
                int medicalRecordId = -1;
                if (rs.next()) {
                    medicalRecordId = rs.getInt(1);
                    medicalRecord.setId(medicalRecordId);
                    addMedications(addPrescribedMedications, medicalRecord);
                    conn.commit();
                } else
                    conn.rollback();
                return medicalRecordId;
            } catch (SQLIntegrityConstraintViolationException e) {
                conn.rollback();
                throw new FOREIGN_KEY_ERROR();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(MedicalRecord medicalRecord) {
        try (Connection conn = pool.getConnection();
             PreparedStatement updateMedicalRecord = conn.prepareStatement(SQLQueries.UPDATE_MEDICAL_RECORD);
             PreparedStatement getPrescribedMedications = conn.prepareStatement(SQLQueries.GET_PRESCRIBED_MEDICATIONS);
             PreparedStatement deletePrescribedMedications = conn.prepareStatement(SQLQueries.DELETE_PRESCRIBED_MEDICATIONS);
             PreparedStatement addPrescribedMedications = conn.prepareStatement(SQLQueries.ADD_PRESCRIBED_MEDICATIONS);
        ) {
            boolean rollback = false;
            conn.setAutoCommit(false);
            deletePrescribedMedications.setInt(1, medicalRecord.getId());
            deletePrescribedMedications.executeUpdate();
            if (!medicalRecord.getMedications().isEmpty()) {
                addMedications(addPrescribedMedications, medicalRecord);
                if (addPrescribedMedications.executeUpdate() == medicalRecord.getMedications().size()) {
                    updateMedicalRecord.setInt(1, medicalRecord.getIdDoctor());
                    updateMedicalRecord.setString(2, medicalRecord.getDiagnosis());
                    updateMedicalRecord.setDate(3, Date.valueOf(medicalRecord.getDate().toString()));
                    updateMedicalRecord.setInt(4, medicalRecord.getId());
                    if (updateMedicalRecord.executeUpdate() == 1)
                        conn.commit();
                    else
                        rollback = true;
                } else
                    rollback = true;
            } else
                rollback = true;
            if (rollback)
                conn.rollback();
             /*conn.setAutoCommit(false);
            getPrescribedMedications.setInt(1, medicalRecord.getId());
            getPrescribedMedications.executeUpdate();
            medicalRecordsMapper.compareMedications(getPrescribedMedications.getGeneratedKeys(),medicalRecord.getMedications());*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addMedications(PreparedStatement addPrescribedMedications, MedicalRecord medicalRecord) throws SQLException {
        medicalRecord.getMedications().forEach(m -> {
            try {
                addPrescribedMedications.setInt(1, medicalRecord.getId());
                addPrescribedMedications.setString(2, m.getMedicationName());
                addPrescribedMedications.setString(3, m.getDosage());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
