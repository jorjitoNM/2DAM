package com.hospital_jpa.domain.service;

import com.hospital_jpa.dao.model.Credential;
import com.hospital_jpa.dao.repository.CredentialRepository;
import com.hospital_jpa.domain.model.CredentialUI;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CredentialService {
    private final CredentialRepository credentialRepository;
    private final RoleService roleService;

    public CredentialService(CredentialRepository credentialRepository, RoleService roleService) {
        this.credentialRepository = credentialRepository;
        this.roleService = roleService;
    }

    public boolean login(CredentialUI userCredentialsUI) {
        Optional<Credential> dbCredential = credentialRepository.findByUserName(userCredentialsUI.getUsername());
        dbCredential.ifPresent(roleService::saveFileUser);
        return dbCredential.map(credential -> credential.getPassword().equals(userCredentialsUI.getPassword())).orElse(false);
    }
}
