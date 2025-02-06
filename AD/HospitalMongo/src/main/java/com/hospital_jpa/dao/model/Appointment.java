package com.hospital_jpa.dao.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Appointment {
    private int appointmentId;
    private int doctorId;
    private Patient patient;
    private LocalDate appointmentDate;
}
