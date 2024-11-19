package com.hospitalcrud.dao.respositories.spring;

import com.hospitalcrud.dao.mappers.spring_mappers.MapSpringMedicalRecords;
import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.respositories.MedicalRecordsRepository;
import com.hospitalcrud.dao.respositories.MedicationsRepository;
import com.hospitalcrud.dao.utilities.SQLQueries;
import com.hospitalcrud.domain.error.FOREIGN_KEY_ERROR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@Profile("spring")
public class SpringMedicalRecordsRepository implements MedicalRecordsRepository {
    private final MapSpringMedicalRecords medicalRecordsMapper;
    private final MedicationsRepository medicationsRepository;
    @Autowired
    private JdbcClient jdbcClient;

    public SpringMedicalRecordsRepository(MapSpringMedicalRecords medicalRecordsMapper, MedicationsRepository medicationsRepository) {
        this.medicalRecordsMapper = medicalRecordsMapper;
        this.medicationsRepository = medicationsRepository;
    }

    @Override
    public List<MedicalRecord> getAll(int idPatient) {
        return jdbcClient.sql(SQLQueries.GET_MEDICAL_RECORDS).param("id", idPatient).query(medicalRecordsMapper).list();
    }

    @Override
    @Transactional
    public void delete(int medicalRecordId) {
        medicationsRepository.deleteMedicalRecordMedications(medicalRecordId);
        jdbcClient.sql(SQLQueries.DELETE_MEDICAL_RECORD).param("id",medicalRecordId).query();
    }

    @Override
    public int save(MedicalRecord medicalRecord) {
        return 0;
    }

    @Override
    public void update(MedicalRecord medicalRecord) {

    }

    @Override
    public void deletePatientMedicalRecords(int patientId) {
        jdbcClient.sql(SQLQueries.DELETE_MEDICAL_RECORD).param("id",patientId).query();
    }
}
