package com.hospitalcrud.dao.respositories.statiC;

import com.hospitalcrud.dao.model.Credential;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Data
@Repository
public class CredentialRepository {
    private final Credential root = new Credential("root", "quevedo2dam",-1);
    private final List<Credential> credentials = new ArrayList<>();

    public void add(Credential credential) {
        credentials.add(credential);
    }

    public boolean login(Credential userCredentials) {
        return userCredentials.getUserName().equals(root.getUserName()) && userCredentials.getPassword().equals(root.getPassword());
    }
}
