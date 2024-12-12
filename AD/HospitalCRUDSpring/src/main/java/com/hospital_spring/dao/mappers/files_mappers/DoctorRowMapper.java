package com.hospital_spring.dao.mappers.files_mappers;

import com.hospital_spring.dao.model.Doctor;
import com.hospital_spring.common.Constantes;
import org.springframework.stereotype.Component;

@Component
public class DoctorRowMapper {
    public Doctor mapRow (String doctor) {
        String[] parsed = doctor.split(Constantes.SEPARADOR_CSV);
        return new Doctor(Integer.parseInt(parsed[0].trim()),parsed[1].trim(),parsed[2].trim(),"");
    }
}
