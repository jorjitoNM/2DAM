package com.hospital_jpa.dao.respositories;

import com.hospital_jpa.dao.model.Credential;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
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
    public void update(Credential credential) {

    }

    @Override
    public Credential get(String username) {
        Credential c = null;
        return c;
    }
}
