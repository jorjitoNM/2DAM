package com.hospitalcrud.dao.respositories.spring;

import com.hospitalcrud.dao.model.Medication;
import com.hospitalcrud.dao.respositories.MedicationsRepository;
import com.hospitalcrud.dao.utilities.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("jdbc")
public class SpringMedicationsRepository implements MedicationsRepository {
    @Autowired
    private JdbcClient jdbcClient;
    @Override
    public List<Medication> getPrescribedMedications(int medicalRecordId) {
        return List.of();
    }

    @Override
    public List<String> getAll() {
        return List.of();
    }

    @Override
    public void deletePatientMedications(int patientId) {
        jdbcClient.sql(SQLQueries.DELETE_PRESCRIBED_MEDICATIONS).param("id",patientId).query();
    }
}
