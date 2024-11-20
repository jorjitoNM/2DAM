package com.hospitalcrud.dao.respositories.spring;

import com.hospitalcrud.dao.mappers.spring_mappers.MapSpringMedicalRecords;
import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.respositories.MedicalRecordsRepository;
import com.hospitalcrud.dao.respositories.MedicationsRepository;
import com.hospitalcrud.dao.utilities.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public void delete(MedicalRecord medicalRecord) {
        if (medicalRecord.getIdPatient() == -1) {
            medicationsRepository.deleteMedicalRecordMedications(medicalRecord.getId());
            jdbcClient.sql(SQLQueries.DELETE_MEDICAL_RECORD).param("id",medicalRecord.getId()).update();
        } else {
            jdbcClient.sql(SQLQueries.DELETE_PATIENT_MEDICAL_RECORDS).param("id",medicalRecord.getIdPatient()).update();
        }
    }

    @Override
    public int save(MedicalRecord medicalRecord) {
        return 0;
    }

    @Override
    public void update(MedicalRecord medicalRecord) {

    }
}
