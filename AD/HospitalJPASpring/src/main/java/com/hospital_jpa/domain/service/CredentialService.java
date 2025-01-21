package com.hospital_jpa.domain.service;

import com.hospital_jpa.dao.repository.CredentialRepository;
import com.hospital_jpa.domain.model.CredentialUI;
import org.springframework.stereotype.Service;


@Service
public class CredentialService {
    private final CredentialRepository credentialRepository;
    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public boolean login(CredentialUI userCredentialsUI) {
        return credentialRepository.findByUserName(userCredentialsUI.getUsername())
                .map(value -> value.getPassword().equals(userCredentialsUI.getPassword())).orElse(false);
    }
}
