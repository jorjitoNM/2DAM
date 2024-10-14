package com.hospitalcrud.dao.mappers;

import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.model.Medication;
import com.hospitalcrud.dao.model.Patient;
import com.hospitalcrud.dao.utilities.Constantes;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MedicalRecordRowMapper {
    public MedicalRecord mapRow(String medicalRecord) {
        return null;
    }

    private List<Medication> parseMedications(String s) {
        List<Medication> medications = new ArrayList<>();
        String[] lines = s.split(Constantes.SEPARADOR_SECUNDARIO);
        for (int i = 0; i < lines.length; i++) {
            //medications.add(new Medication(parseMedication(lines[i])));
        }
        return medications;
    }

    private Medication parseMedication(String line) {
        return null;
    }
}
