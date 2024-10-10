package com.hospitalcrud.dao.mappers;

import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.model.Patient;
import com.hospitalcrud.dao.utilities.Constantes;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MedicalRecordRowMapper {
    public MedicalRecord mapRow(String medicalRecord) {
        String[] parsed = medicalRecord.split(Constantes.SEPARADOR_CSV);
        return new MedicalRecord();
    }
}
