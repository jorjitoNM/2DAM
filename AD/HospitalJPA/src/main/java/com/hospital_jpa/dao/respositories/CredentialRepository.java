package com.hospital_jpa.dao.respositories;

import com.hospital_jpa.dao.model.Credential;
import com.hospital_jpa.dao.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CredentialRepository implements com.hospital_jpa.dao.interfaces.CredentialRepository {
    @Override
    public List<Credential> getAll() {
        return List.of();
    }

    @Override
    public boolean delete(int patient_id) {
        return false;
    }

    @Override
    public void save(Patient patient) {

    }

    @Override
    public void update(Credential credential) {

    }

    @Override
    public Credential get(String username) {
        return null;
    }
}
