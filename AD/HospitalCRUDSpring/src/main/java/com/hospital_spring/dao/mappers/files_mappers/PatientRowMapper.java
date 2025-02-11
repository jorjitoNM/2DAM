package com.hospital_spring.dao.mappers.files_mappers;

import com.hospital_spring.dao.model.Patient;
import com.hospital_spring.common.Constantes;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class PatientRowMapper {
    public Patient mapRow(String patient) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] parsed = patient.split(Constantes.SEPARADOR_CSV);
        return new Patient(Integer.parseInt(parsed[0].trim()),parsed[1].trim(),LocalDate.parse(parsed[2].trim(),formatter),parsed[3].trim());
    }
}
