package com.hospitalcrud.dao.respositories;

import com.hospitalcrud.dao.model.Credential;

import java.util.List;

public interface CredentialRepository {

    List<Credential> getAll();
    boolean delete(int patient_id);
    void save(Credential credential);
    void update(Credential credential);
    Credential get(String username);
}
