package com.hospital_jpa.dao.respositories;

import com.hospital_jpa.dao.model.MedicalRecord;
import com.hospital_jpa.dao.model.Medication;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Log4j2
public class MedicationsRepository implements com.hospital_jpa.dao.interfaces.MedicationsRepository {

    @Override
    public List<Medication> getPrescribedMedications(int medicalRecordId) {
        return List.of();
    }

    @Override
    public List<Medication> getAll() {
        List<Medication> medications = new ArrayList<>();
        return medications;
    }

    @Override
    public void deletePatientMedications(int patientId) {

    }

    @Override
    public void deleteMedicalRecordMedications(int medicalRecordId) {

    }

    @Override
    public void save(MedicalRecord medicalRecord) {

    }

    @Override
    public void update(MedicalRecord medicalRecord) {

    }
}
