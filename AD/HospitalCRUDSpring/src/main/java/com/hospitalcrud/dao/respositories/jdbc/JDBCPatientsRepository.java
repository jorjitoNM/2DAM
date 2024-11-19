package com.hospitalcrud.dao.respositories.jdbc;

import com.hospitalcrud.dao.mappers.jdbc_mappers.MapPatients;
import com.hospitalcrud.dao.model.Patient;
import com.hospitalcrud.dao.respositories.PatientRepository;
import com.hospitalcrud.dao.utilities.DBConnectionPool;
import com.hospitalcrud.dao.utilities.SQLQueries;
import com.hospitalcrud.domain.error.DUPLICATED_USERNAME;
import com.hospitalcrud.domain.error.FOREIGN_KEY_ERROR;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Profile("jdbc")
@Repository
public class JDBCPatientsRepository implements PatientRepository {
    private final MapPatients patientsMapper;
    private final DBConnectionPool pool;

    public JDBCPatientsRepository(MapPatients patientsMapper, DBConnectionPool pool) {
        this.patientsMapper = patientsMapper;
        this.pool = pool;
    }


    @Override
    public List<Patient> getAll() {
        try (Connection con = pool.getConnection();
             Statement getPatients = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ) {
            ResultSet resultSet = getPatients.executeQuery(SQLQueries.GET_ALL_PATIENTS);
            return patientsMapper.readRS(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(Patient patient) {
        try (Connection con = pool.getConnection();
             PreparedStatement insertPatient = con.prepareStatement(SQLQueries.INSERT_PATIENT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertCredential = con.prepareStatement(SQLQueries.INSERT_CREDENTIAL)
        ) {
            con.setAutoCommit(false);
            setPatientValues(patient, insertPatient).executeUpdate();
            ResultSet rs = insertPatient.getGeneratedKeys();
            if (rs.next()) {
                insertCredential.setString(1, patient.getCredential().getUserName());
                insertCredential.setString(2, patient.getCredential().getPassword());
                insertCredential.setInt(3, rs.getInt(1));
                insertCredential.setNull(4, 0);
                try {
                    insertCredential.executeUpdate();
                    con.commit();
                    return rs.getInt(1);
                } catch (SQLException e) {
                    con.rollback();
                    throw new DUPLICATED_USERNAME();
                }
            } else {
                con.rollback();
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Patient patient) {
        try (Connection con = pool.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.UPDATE_PATIENT)
        ) {
            preparedStatement.setInt(4, patient.getId());
            setPatientValues(patient, preparedStatement).executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    @Override
    public boolean delete(int patientId, boolean confirmation) {
        int result = 0;
        try (Connection con = pool.getConnection();
             PreparedStatement deletePatient = con.prepareStatement(SQLQueries.DELETE_PATIENT);
             PreparedStatement deleteCredential = con.prepareStatement(SQLQueries.DELETE_CREDENTIAL);
             PreparedStatement deleteMedicalRecords = con.prepareStatement(SQLQueries.DELETE_PATIENT_MEDICAL_RECORDS);
             PreparedStatement deletePrescribedMedications = con.prepareStatement(SQLQueries.DELETE_PATIENT_PRESCRIBED_MEDICATIONS);

        ) {
            boolean rollback = false;
            con.setAutoCommit(false);
            deletePrescribedMedications.setInt(1, patientId);
            deletePrescribedMedications.executeUpdate();
            deleteMedicalRecords.setInt(1, patientId);
            if (deleteMedicalRecords.executeUpdate() > 0) {
                deleteCredential.setInt(1, patientId);
                if (deleteCredential.executeUpdate() > 0) {
                    deletePatient.setInt(1, patientId);
                    try {
                        result = deletePatient.executeUpdate();
                        con.commit();
                    } catch (SQLIntegrityConstraintViolationException e) {
                        con.rollback();
                        throw new FOREIGN_KEY_ERROR();
                    }
                } else {
                    rollback = true;
                }
            } else
                rollback = true;
            if (rollback)
                con.rollback();
            else
                con.commit();
            return result == 1;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    private PreparedStatement setPatientValues(Patient patient, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, patient.getName());
        preparedStatement.setDate(2, Date.valueOf(patient.getBirthDate()));
        preparedStatement.setString(3, patient.getPhone());
        return preparedStatement;
    }
}
