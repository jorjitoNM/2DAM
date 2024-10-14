package com.hospitalcrud.dao.respositories;

import com.hospitalcrud.dao.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository {
    List<Patient> getAll();
    int save(Patient patient);
    void update(Patient patient);
    void delete(int patientId, boolean confirmation);
}
