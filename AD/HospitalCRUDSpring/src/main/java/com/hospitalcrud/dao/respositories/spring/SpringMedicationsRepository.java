package com.hospitalcrud.dao.respositories.spring;

import com.hospitalcrud.dao.mappers.spring_mappers.MapSpringMedications;
import com.hospitalcrud.dao.model.Medication;
import com.hospitalcrud.dao.respositories.MedicationsRepository;
import com.hospitalcrud.dao.utilities.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("spring")
public class SpringMedicationsRepository implements MedicationsRepository {
    private final MapSpringMedications medicationsMapper;
    @Autowired
    private JdbcClient jdbcClient;

    public SpringMedicationsRepository(MapSpringMedications medicationsMapper) {
        this.medicationsMapper = medicationsMapper;
    }

    @Override
    public List<Medication> getPrescribedMedications(int medicalRecordId) {
        return jdbcClient.sql(SQLQueries.GET_PRESCRIBED_MEDICATIONS)
                .param(1,medicalRecordId).query(medicationsMapper).list();
    }

    @Override
    public List<String> getAll() {
        return List.of();
    }

    @Override
    public void deletePatientMedications(int patientId) {
        jdbcClient.sql(SQLQueries.DELETE_PATIENT_PRESCRIBED_MEDICATIONS).param("id",patientId).query();
    }

    @Override
    public void deleteMedicalRecordMedications(int medicalRecordId) {
        jdbcClient.sql(SQLQueries.DELETE_PRESCRIBED_MEDICATIONS).query();
    }
}
