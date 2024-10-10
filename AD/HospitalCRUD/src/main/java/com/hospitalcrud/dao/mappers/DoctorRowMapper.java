package com.hospitalcrud.dao.mappers;

import com.hospitalcrud.dao.model.Doctor;
import com.hospitalcrud.dao.utilities.Constantes;
import org.springframework.stereotype.Component;

@Component
public class DoctorRowMapper {
    public Doctor mapRow (String doctor) {
        String[] parsed = doctor.split(Constantes.SEPARADOR_CSV);
        return new Doctor(Integer.parseInt(parsed[0].trim()),parsed[1].trim(),parsed[2].trim());
    }
}
