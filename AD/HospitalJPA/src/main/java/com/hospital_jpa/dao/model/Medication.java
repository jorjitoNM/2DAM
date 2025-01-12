package com.hospital_jpa.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prescribed_medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id")
    private int id;
    @Column(name = "medication_name")
    private String medicationName;
    @Column(name = "record_id")
    private Integer medRecordId;
    @Column
    private String dosage;


    public Medication(int id, String medicationName, int medRecordId) {
        this.id = id;
        this.medicationName = medicationName;
        this.medRecordId = medRecordId;
        this.dosage = "";
    }
    public Medication(String medicationName, int medRecordId, String dosage) {
        this.id = 0;
        this.medicationName = medicationName;
        this.medRecordId = medRecordId;
        this.dosage = "";
    }
}
