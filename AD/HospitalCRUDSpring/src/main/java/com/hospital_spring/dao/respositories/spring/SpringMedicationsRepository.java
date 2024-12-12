package com.hospital_spring.dao.respositories.spring;

import com.hospital_spring.dao.mappers.spring_mappers.MapSpringMedications;
import com.hospital_spring.dao.model.MedicalRecord;
import com.hospital_spring.dao.model.Medication;
import com.hospital_spring.dao.respositories.MedicationsRepository;
import com.hospital_spring.dao.utilities.SQLQueriesSpring;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("spring")
public class SpringMedicationsRepository implements MedicationsRepository {
    private final MapSpringMedications medicationsMapper;
    private final JdbcClient jdbcClient;

    public SpringMedicationsRepository(MapSpringMedications medicationsMapper, JdbcClient jdbcClient) {
        this.medicationsMapper = medicationsMapper;
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Medication> getPrescribedMedications(int medicalRecordId) {
        return jdbcClient.sql(SQLQueriesSpring.GET_PRESCRIBED_MEDICATIONS)
                .param("id",medicalRecordId)
                .query(medicationsMapper).list();
    }

    @Override
    public List<Medication> getAll() {
        return jdbcClient.sql(SQLQueriesSpring.GET_ALL_MEDICATIONS)
                .query(medicationsMapper).list();
    }

    @Override
    public void deletePatientMedications(int patientId) {
        jdbcClient.sql(SQLQueriesSpring.DELETE_PATIENT_PRESCRIBED_MEDICATIONS)
                .param(1,patientId).update();
    }

    @Override
    public void deleteMedicalRecordMedications(int medicalRecordId) {
        jdbcClient.sql(SQLQueriesSpring.DELETE_PRESCRIBED_MEDICATIONS)
                .param("id",medicalRecordId).update();
    }

    @Override
    public void save(MedicalRecord medicalRecord) {
        medicalRecord.getMedications().forEach(m -> jdbcClient.sql(SQLQueriesSpring.INSERT_MEDICATION)
                .param("record_id",medicalRecord.getId())
                .param("medication_name",m.getMedicationName())
                .param("dosage",m.getDosage())
                .update());
    }

    @Override
    public void update(MedicalRecord medicalRecord) {
        deleteMedicalRecordMedications(medicalRecord.getId());
        if (!medicalRecord.getMedications().isEmpty())
            save(medicalRecord);
    }
}
