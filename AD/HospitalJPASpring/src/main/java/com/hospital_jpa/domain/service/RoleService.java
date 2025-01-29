package com.hospital_jpa.domain.service;

import com.hospital_jpa.dao.model.Credential;
import com.hospital_jpa.dao.model.FileUser;
import com.hospital_jpa.dao.model.UserType;
import com.hospital_jpa.dao.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public void saveFileUser(Credential credential) {
        FileUser fileUser;
        if (credential.getPatient() == null)
            if (credential.getDoctorId() == null)
                fileUser = new FileUser(-1,UserType.ADMIN);
            else
                fileUser = new FileUser(credential.getDoctorId(),UserType.DOCTOR);
        else
            fileUser = new FileUser(credential.getPatient().getId(),UserType.PATIENT);
        repository.saveFileUser(fileUser);
    }

    public FileUser getFileUser() {
        return repository.loadFileUser();
    }
}
