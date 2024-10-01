package com.hospitalcrud.ui;

import com.hospitalcrud.dao.model.Credential;
import com.hospitalcrud.domain.service.CredentialService;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestCredential {

    private final CredentialService credentialService;

    public RestCredential(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/login")
    public boolean login(@RequestBody Credential userCredentials) {
        return credentialService.login(userCredentials);
    }
}