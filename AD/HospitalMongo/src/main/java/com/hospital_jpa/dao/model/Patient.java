package com.hospital_jpa.dao.model;

import com.hospital_jpa.domain.model.PatientUI;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Patient {
    private ObjectId _id;
    private String name;
    private LocalDate birthDate;
    private String phone;
    private List<Payment> payments;

    public Patient(String name, LocalDate birthDate, String phone) {
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
        this.payments = new ArrayList<>();
    }

    public Patient(ObjectId _id) {
        this._id = _id;
    }

    public Patient(ObjectId id, String name, LocalDate birthDate, String phone) {
        this._id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
    }
}
