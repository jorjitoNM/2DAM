package com.hospital_jpa.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecord {
    private int id;
    private Patient patient;
    private int idDoctor;
    private String diagnosis;
    private LocalDate date;
    private List<Medication> medications;

    public MedicalRecord(Patient patient, int idDoctor, String diagnosis, LocalDate date, List<Medication> medications) {
        this.patient = patient;
        this.idDoctor = idDoctor;
        this.diagnosis = diagnosis;
        this.date = date;
        this.medications = medications;
    }

    public MedicalRecord (int id) {
        this.id = id;
    }
}
