package com.hospital_spring.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialUI {
    private String username;
    private String password;
}
