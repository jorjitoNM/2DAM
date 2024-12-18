package com.hospital_jpa.dao.model;

import com.hospital_jpa.dao.utilities.JPAQueries;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "patients")
@NamedQuery(name = JPAQueries.GET_ALL_PATIENTS, query = "from Patient")
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
    @Transient
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
}
