package com.hospitalcrud.dao.respositories.statiC;

import com.hospitalcrud.dao.model.Credential;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("static")
public class StaticCredentialRepository implements com.hospitalcrud.dao.respositories.CredentialRepository {
    private final Credential root = new Credential("root", "quevedo2dam",-1);
    private final List<Credential> credentials = new ArrayList<>();

    public void save(Credential credential) {
        credentials.add(credential);
    }

    public boolean login(Credential userCredentials) {
        return userCredentials.getUserName().equals(root.getUserName()) && userCredentials.getPassword().equals(root.getPassword());
    }

    @Override
    public List<Credential> getAll() {
        return List.of();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Credential credential) {

    }
}
