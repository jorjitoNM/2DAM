package com.hospital_jpa.dao.respositories;

import com.hospital_jpa.dao.model.MedicalRecord;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Log4j2
public class MedicalRecordsRepository implements com.hospital_jpa.dao.interfaces.MedicalRecordsRepository {


    @Override
    public List<MedicalRecord> getAll(int idPatient) {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        return medicalRecords;
    }

    @Override
    public void delete(MedicalRecord medicalRecord) {
    }

    @Override
    public int save(MedicalRecord medicalRecord) {
        return medicalRecord.getId();
    }

    @Override
    public void update(MedicalRecord medicalRecord) {
    }
}
