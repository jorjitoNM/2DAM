package com.hospital_jpa.dao.respositories;

import com.hospital_jpa.dao.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordsRepository implements com.hospital_jpa.dao.interfaces.MedicalRecordsRepository {
    @Override
    public List<MedicalRecord> getAll(int idPatient) {
        return List.of();
    }

    @Override
    public void delete(MedicalRecord medicalRecord) {

    }

    @Override
    public int save(MedicalRecord medicalRecord) {
        return 0;
    }

    @Override
    public void update(MedicalRecord medicalRecord) {

    }
}
