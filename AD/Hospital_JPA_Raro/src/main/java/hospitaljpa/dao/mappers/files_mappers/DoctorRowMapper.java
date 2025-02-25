package hospitaljpa.dao.mappers.files_mappers;


import com.hospital_jpa.common.Constantes;
import com.hospital_jpa.dao.model.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorRowMapper {
    public Doctor mapRow (String doctor) {
        String[] parsed = doctor.split(Constantes.SEPARADOR_CSV);
        return new Doctor(Integer.parseInt(parsed[0].trim()),parsed[1].trim(),parsed[2].trim(),"");
    }
}
