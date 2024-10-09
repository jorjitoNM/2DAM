package com.hospitalcrud.dao.model;

import com.hospitalcrud.dao.utilities.Constantes;
import com.hospitalcrud.domain.model.PatientUI;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Patient {
    private int id;
    private String name;
    private LocalDate birthDate;
    private String phone;
    private Credential credential;

    public Patient(int id, String name, LocalDate birthDate, String phone) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public Patient(int id, String name, LocalDate birthDate, String phone, Credential credential) {

    }

    public String toStringFichero() {
        return id + Constantes.SEPARADOR_CSV + name + Constantes.SEPARADOR_CSV
                + birthDate + Constantes.SEPARADOR_CSV + phone + Constantes.SEPARADOR_CSV;
    }
}
