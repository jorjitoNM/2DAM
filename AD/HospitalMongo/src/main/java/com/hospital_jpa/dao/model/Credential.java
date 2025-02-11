package com.hospital_jpa.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credential {
    private ObjectId _id;
    private String username;
    private String password;
    private ObjectId patient;
    private ObjectId doctorId;


    public Credential(String username, String password, ObjectId patient) {
        this.username = username;
        this.password = password;
        this.patient = patient;
        this.doctorId = null;
    }
}
