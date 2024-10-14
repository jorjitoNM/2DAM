package com.hospitalcrud.dao.respositories;

import com.hospitalcrud.dao.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordsRepository {
    List<MedicalRecord> getAll(int idPatient);
    void delete(int id);
    int save(MedicalRecord medicalRecord);
    void update(MedicalRecord medicalRecord);

}
