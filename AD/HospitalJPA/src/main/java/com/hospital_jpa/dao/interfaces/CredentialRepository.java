package com.hospital_jpa.dao.interfaces;

import com.hospital_jpa.dao.model.Credential;
import com.hospital_jpa.dao.model.Patient;

import java.util.List;

public interface CredentialRepository {

    List<Credential> getAll();
    boolean delete(int patient_id);
    void save(Patient patient);
    void update(Credential credential);
    Credential get(String username);
}
