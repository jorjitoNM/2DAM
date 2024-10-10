package com.hospitalcrud.dao.model;

import com.hospitalcrud.dao.utilities.Constantes;
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
    private List<Medication> medications; //hace falta mapear las medicaciones

    public String toStringFichero() {
        return id + Constantes.SEPARADOR_CSV + idPatient + Constantes.SEPARADOR_CSV
                + idDoctor + Constantes.SEPARADOR_CSV + diagnosis + Constantes.SEPARADOR_CSV + date + Constantes.SEPARADOR_CSV + medications + Constantes.SEPARADOR_CSV;
    }
}
