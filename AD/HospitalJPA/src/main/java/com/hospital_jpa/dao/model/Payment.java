package com.hospital_jpa.dao.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "patient_payments")
@NamedQuery(name = "getPaymentsByPatient", query = "select sum(Payment.amount),Payment.patient.id from Payment group by Payment.patient.id")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int id;
    @Column
    private int amount;
    @Column(name = "payment_date")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}