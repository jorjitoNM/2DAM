package com.hospital_jpa.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medication {
    private int id;
    private String medicationName;
    private MedicalRecord medicalRecord;
    private String dosage;


    public Medication(int id, String medicationName) {
        this.id = id;
        this.medicationName = medicationName;
        this.dosage = "";
    }
    public Medication(String medicationName, String dosage) {
        this.medicationName = medicationName;
        this.dosage = dosage;
    }
    public Medication(MedicalRecord medicalRecord, String medicationName, String dosage) {
        this.medicalRecord = medicalRecord;
        this.medicationName = medicationName;
        this.dosage = dosage;
    }
}