package hospitaljpa.dao.mappers.files_mappers;

import com.hospitalcrud.common.Constantes;
import com.hospitalcrud.dao.model.Patient;
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
