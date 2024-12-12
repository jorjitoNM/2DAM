package com.hospital_spring.dao.respositories;

import com.hospital_spring.dao.model.Patient;

import java.util.List;


public interface PatientRepository {
    List<Patient> getAll();
    int save(Patient patient);
    void update(Patient patient);
    boolean delete(int patientId,boolean confirmation);
}
