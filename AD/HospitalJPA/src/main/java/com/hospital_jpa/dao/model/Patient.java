package com.hospital_jpa.dao.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "patients")
@NamedQueries({
        @NamedQuery(name = "getAllPatients", query = "from Patient"),
        @NamedQuery(name = "deletePatientMedicalRecords", query = "delete from MedicalRecord m where m.patient.id = :patient_id"),
        @NamedQuery(name = "deletePatientPrescribedMedications", query = "delete from Medication pm where pm.medicalRecord.id in (select mr.id from MedicalRecord mr where mr.patient.id = :patient_id)")
})

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate birthDate;
    @Column(name = "phone")
    private String phone;
    @OneToOne(mappedBy = "patient", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private Credential credential;

    public Patient(int id, String name, LocalDate birthDate, String phone) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public Patient(int id, String name, LocalDate birthDate, String phone, Credential credential) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
        this.credential = credential;
    }

    public Patient(int id) {
        this.id = id;
    }
}
