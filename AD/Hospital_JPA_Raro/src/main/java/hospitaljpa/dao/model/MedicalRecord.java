package hospitaljpa.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecord {
    private int id;
    private int idPatient;
    private int idDoctor;
    private String diagnosis;
    private LocalDate date;
    private List<Medication> medications;

    public MedicalRecord(int id, int idPatient, int idDoctor, String diagnosis, LocalDate date) {
        this.id = id;
        this.idPatient = idPatient;
        this.idDoctor = idDoctor;
        this.diagnosis = diagnosis;
        this.date = date;
    }
}
