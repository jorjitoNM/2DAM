package com.hospital_jpa.dao.interfaces;

import com.hospital_jpa.dao.model.Credential;
import org.bson.types.ObjectId;

import java.util.List;

public interface CredentialRepository {

    void save(Credential credential);
    int delete(ObjectId patientId);
    void update(Credential credential);
    Credential get(String username);
}
