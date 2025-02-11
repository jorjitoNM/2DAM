package com.hospital_jpa.domain.model;

import com.hospital_jpa.dao.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientUI {
    private int id;
    private String name;
    private LocalDate birthDate;
    private int paid;
    private String password;
    private String phone;
    private String userName;

    public PatientUI(int id, String name, LocalDate birthDate, int paid, String phone) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.paid = paid;
        this.phone = phone;
    }

    public PatientUI(Patient patient) {
        //id = patient.get_id();
        name = patient.getName();
        birthDate = patient.getBirthDate();
        paid = 0;
        password = "";
        phone = patient.getPhone();
        userName = "";
    }

    public Patient toPatient () {
        return new Patient(this.name,this.birthDate,this.phone);
    }
    public Patient toPatient (ObjectId _id) {
        return new Patient(_id,this.name,this.birthDate,this.phone);
    }
}
