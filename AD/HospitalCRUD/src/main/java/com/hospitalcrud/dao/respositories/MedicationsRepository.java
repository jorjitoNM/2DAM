package com.hospitalcrud.dao.respositories;

import com.hospitalcrud.dao.model.Medication;

import java.util.List;

public interface MedicationsRepository {
    List<Medication> getPrescribedMedications (int medicalRecordId);
    List<String> getAll();
}
