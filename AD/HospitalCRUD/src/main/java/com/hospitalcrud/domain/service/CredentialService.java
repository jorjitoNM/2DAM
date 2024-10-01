package com.hospitalcrud.domain.service;

import com.hospitalcrud.dao.model.Credential;
import com.hospitalcrud.dao.respositories.CredentialRepository;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {
    private final CredentialRepository credentialRepository;
    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public boolean login(Credential userCredentials) {
        return credentialRepository.login(userCredentials);
    }
}
