package com.hospitalcrud.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class MedicalRecord {
    private int id;
    private int idPatient;
    private int idDoctor;
    private String diagnosis;
    private LocalDate date;
    private List<Medication> medications;

}
