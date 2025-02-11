package com.hospital_jpa.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUser {
    private int id;
    private UserType userType;
}
