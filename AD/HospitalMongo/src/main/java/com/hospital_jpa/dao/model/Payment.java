package com.hospital_jpa.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Payment {
    private int id;
    private int amount;
    private LocalDate date;
    private Patient patient;

    public Payment(int id, long amount) {
        this.patient = new Patient(id);
        this.amount = (int) amount;
    }
}