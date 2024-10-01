package com.hospitalcrud.dao.respositories;

import com.hospitalcrud.dao.model.Credential;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class CredentialRepository {

    public void add(Credential credential) {

    }

    public boolean login(Credential userCredentials) {
        return true;
                //userCredentials.getUserName().equals() && userCredentials.getPassword().equals();
    }
}
