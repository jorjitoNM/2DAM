package org.example.hospitaljpa.dao.respositories.spring;

import com.hospitalcrud.common.Constantes;
import com.hospitalcrud.dao.mappers.spring_mappers.MapSpringMedicalRecords;
import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.respositories.MedicalRecordsRepository;
import com.hospitalcrud.dao.respositories.MedicationsRepository;
import com.hospitalcrud.dao.utilities.SQLQueriesSpring;
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
public class SpringMedicalRecordsRepository implements MedicalRecordsRepository {
    private final MapSpringMedicalRecords medicalRecordsMapper;
    private final MedicationsRepository medicationsRepository;
    private final JdbcClient jdbcClient;

    public SpringMedicalRecordsRepository(MapSpringMedicalRecords medicalRecordsMapper, MedicationsRepository medicationsRepository, JdbcClient jdbcClient) {
        this.medicalRecordsMapper = medicalRecordsMapper;
        this.medicationsRepository = medicationsRepository;
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<MedicalRecord> getAll(int idPatient) {
        return jdbcClient.sql(SQLQueriesSpring.GET_MEDICAL_RECORDS).param("id", idPatient).query(medicalRecordsMapper).list();
    }

    @Override
    @Transactional
    public void delete(MedicalRecord medicalRecord) {
        if (medicalRecord.getIdPatient() == -1) {
            medicationsRepository.deleteMedicalRecordMedications(medicalRecord.getId());
            jdbcClient.sql(SQLQueriesSpring.DELETE_MEDICAL_RECORD).param("id",medicalRecord.getId()).update();
        } else
            jdbcClient.sql(SQLQueriesSpring.DELETE_PATIENT_MEDICAL_RECORDS).param(1,medicalRecord.getIdPatient()).update();
    }

    @Override
    @Transactional
    public int save(MedicalRecord medicalRecord) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(SQLQueriesSpring.INSERT_MEDICAL_RECORD)
                .param("patient_id",medicalRecord.getIdPatient())
                .param("doctor_id",medicalRecord.getIdDoctor())
                .param("diagnosis",medicalRecord.getDiagnosis())
                .param("admission_date",medicalRecord.getDate())
                .update(keyHolder);
        medicalRecord.setId(Objects.requireNonNull(keyHolder.getKey(), Constantes.ERROR_GENERATING_KEY).intValue());
        medicationsRepository.save(medicalRecord);
        return medicalRecord.getId();
    }

    @Override
    public void update(MedicalRecord medicalRecord) {
        jdbcClient.sql(SQLQueriesSpring.UPDATE_MEDICAL_RECORD)
                .param("doctor_id",medicalRecord.getIdDoctor())
                .param("diagnosis",medicalRecord.getDiagnosis())
                .param("admission_date",medicalRecord.getDate())
                .param("patient_id",medicalRecord.getIdPatient())
                .param("record_id",medicalRecord.getId())
                .update();
        medicationsRepository.update(medicalRecord);
    }
}
