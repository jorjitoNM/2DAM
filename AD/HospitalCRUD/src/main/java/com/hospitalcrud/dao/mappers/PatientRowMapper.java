package com.hospitalcrud.dao.mappers;

import com.hospitalcrud.dao.model.Patient;
import com.hospitalcrud.dao.utilities.Constantes;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PatientRowMapper {
    public Patient mapRow(String patient) {
        String[] parsed = patient.split(Constantes.SEPARADOR_CSV);
        return new Patient(Integer.parseInt(parsed[0].trim()),parsed[1].trim(),LocalDate.parse(parsed[2].trim()),parsed[3].trim());
    }
}
