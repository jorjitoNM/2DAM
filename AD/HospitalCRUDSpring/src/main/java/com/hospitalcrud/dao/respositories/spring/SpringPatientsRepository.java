package com.hospitalcrud.dao.respositories.spring;

import com.hospitalcrud.dao.mappers.spring_mappers.MapSpringPatients;
import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.model.Patient;
import com.hospitalcrud.dao.respositories.*;
import com.hospitalcrud.common.Constantes;
import com.hospitalcrud.dao.utilities.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
@Profile("spring")
public class SpringPatientsRepository implements PatientRepository {
    private final MapSpringPatients patientMapper;
    private final CredentialRepository credentialRepository;
    private final MedicalRecordsRepository medicalRecordsRepository;
    private final PaymentsRepository paymentsRepository;
    private final MedicationsRepository medicationsRepository;
    @Autowired
    private JdbcClient jdbcClient;

    public SpringPatientsRepository(MapSpringPatients patientMapper, CredentialRepository credentialRepository, MedicalRecordsRepository medicalRecordsRepository, PaymentsRepository paymentsRepository, MedicationsRepository medicationsRepository) {
        this.patientMapper = patientMapper;
        this.credentialRepository = credentialRepository;
        this.medicalRecordsRepository = medicalRecordsRepository;
        this.paymentsRepository = paymentsRepository;
        this.medicationsRepository = medicationsRepository;
    }

    @Override
    public List<Patient> getAll() {
        return jdbcClient.sql(SQLQueries.GET_ALL_PATIENTS).query(patientMapper).list();
    }

    @Override
    @Transactional
    public int save(Patient patient) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(SQLQueries.INSERT_PATIENT)
                .param("name", patient.getName())
                .param("date_of_birth",patient.getBirthDate())
                .param("phone",patient.getPhone())
                .update(keyHolder);
        int newId = Objects.requireNonNull(keyHolder.getKey(), Constantes.ERROR_GENERATING_KEY).intValue();
        patient.getCredential().setPatientId(newId);
        credentialRepository.save(patient.getCredential());
        return newId;
    }

    @Override
    public void update(Patient patient) {
        jdbcClient.sql(SQLQueries.UPDATE_PATIENT)
                .param("name", patient.getName())
                .param("date_of_birth",patient.getBirthDate())
                .param("phone",patient.getPhone())
                .param("patient_id",patient.getId())
                .update();
    }

    @Override
    @Transactional
    public boolean delete(int patientId, boolean comfirmation) {
        if (comfirmation) {
            medicationsRepository.deletePatientMedications(patientId);
            medicalRecordsRepository.delete(new MedicalRecord(
                    -1,patientId,-1,null,null));
        }
        credentialRepository.delete(patientId);
        paymentsRepository.deletePatientPayments(patientId);
        jdbcClient.sql(SQLQueries.DELETE_PATIENT).param("id", patientId).update();
        return false;
    }
}
