package com.hospitalcrud.dao.respositories.statiC;

import com.hospitalcrud.dao.model.Credential;
import com.hospitalcrud.dao.model.Patient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("statiC")
public class StaticCredentialRepository implements com.hospitalcrud.dao.respositories.CredentialRepository {
    private final Credential root = new Credential("root", "quevedo2dam", -1, -1);
    private final List<Credential> credentials = new ArrayList<>();

    public void save(Patient patient) {
        credentials.add(patient.getCredential());
    }

    @Override
    public Credential get(String username) {
        if (username.equals(root.getUserName()))
            return new Credential(username, root.getPassword());
        else
            return new Credential(username, "");
    }

    @Override
    public List<Credential> getAll() {
        return List.of();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void update(Credential credential) {

    }
}
