package com.hospital_jpa.dao.respositories;

import com.hospital_jpa.dao.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientRepository implements com.hospital_jpa.dao.interfaces.PatientRepository {
    @Override
    public List<Patient> getAll() {
        return List.of();
    }

    @Override
    public int save(Patient patient) {
        return 0;
    }

    @Override
    public void update(Patient patient) {

    }

    @Override
    public boolean delete(int patientId, boolean confirmation) {
        return false;
    }
}
