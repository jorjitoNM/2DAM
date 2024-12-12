package com.hospital_spring.domain.service;

import com.hospital_spring.dao.respositories.CredentialRepository;
import com.hospital_spring.domain.model.CredentialUI;
import org.springframework.stereotype.Service;


@Service
public class CredentialService {
    private final CredentialRepository credentialRepository;
    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public boolean login(CredentialUI userCredentialsUI) {
        if (credentialRepository.get(userCredentialsUI.getUsername()) == null)
            return false;
        else
            return credentialRepository
                .get(userCredentialsUI.getUsername())
                .getPassword().equals(userCredentialsUI.getPassword());
    }
}
