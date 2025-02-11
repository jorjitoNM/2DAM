package com.hospital_jpa.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credential {
    private int userId;
    private String userName;
    private String password;
    private Patient patient;
    private Integer doctorId;

    public Credential(String userName, String password) {
        this.userName = userName;
        this.password = password;
        doctorId = null;
    }
}
