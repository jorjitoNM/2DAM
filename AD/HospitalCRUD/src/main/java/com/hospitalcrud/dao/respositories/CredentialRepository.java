package com.hospitalcrud.dao.respositories;

import com.hospitalcrud.dao.model.Credential;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredentialRepository {

    List<Credential> getAll();
    void delete(int id);
    void save(Credential credential);
    void update(Credential credential);

    boolean login(Credential credential);
}
