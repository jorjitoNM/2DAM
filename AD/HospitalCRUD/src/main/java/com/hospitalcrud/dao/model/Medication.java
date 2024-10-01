package com.hospitalcrud.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Medication {
    private int id;
    private String medicationName;
    private int medRecordId;
}
