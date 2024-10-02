package com.hospitalcrud.dao.respositories;

import com.hospitalcrud.dao.model.Credential;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class CredentialRepository {
    private final Credential root = new Credential("root", "quevedo2dam",-1);

    public void add(Credential credential) {

    }

    public boolean login(Credential userCredentials) {
        return userCredentials.getUserName().equals(root.getUserName()) && userCredentials.getPassword().equals(root.getPassword());
    }
}
