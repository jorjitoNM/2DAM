package com.hospital_spring.dao.respositories;

import com.hospital_spring.dao.model.Credential;
import com.hospital_spring.dao.model.Patient;

import java.util.List;

public interface CredentialRepository {

    List<Credential> getAll();
    boolean delete(int patient_id);
    void save(Patient patient);
    void update(Credential credential);
    Credential get(String username);
}
