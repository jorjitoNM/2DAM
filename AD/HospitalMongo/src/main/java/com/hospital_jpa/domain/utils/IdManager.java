package com.hospital_jpa.domain.utils;

import com.hospital_jpa.dao.model.Doctor;
import com.hospital_jpa.dao.model.MedicalRecord;
import com.hospital_jpa.dao.model.Patient;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
public class IdManager {

    private final Map<ObjectId,Integer> patientIds;
    private Integer patientAutoIncrement;
    private final Map<ObjectId,Integer> medicalRecordsIds;
    private Integer medicalRecordAutoIncrement;
    private final Map<ObjectId,Integer> doctorIds;
    private Integer doctorAutoIncrement;

    public IdManager() {
        this.patientIds = new HashMap<>();
        this.patientAutoIncrement = 1;
        this.medicalRecordsIds = new HashMap<>();
        this.medicalRecordAutoIncrement = 1;
        this.doctorIds = new HashMap<>();
        this.doctorAutoIncrement = 1;
    }

    public void fillPatientIds (List<Patient> patients) {
        if (patientIds.isEmpty())
            patients.forEach(p -> patientIds.put(p.get_id(), patientAutoIncrement++) );
    }

    public void addPatientId (ObjectId patientId) {
        patientIds.put(patientId, patientAutoIncrement++);
    }

    public ObjectId getPatientTrueID (int id) {
        for (Map.Entry<ObjectId, Integer> entry : patientIds.entrySet()) {
            if (entry.getValue().equals(id)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void fillMedicalRecordsIds (List<MedicalRecord> medicalRecords) {
        if (medicalRecordsIds.isEmpty())
            medicalRecords.forEach(m -> patientIds.put(m.get_id(), medicalRecordAutoIncrement++) );
    }

    public void addMedicalRecordId (ObjectId medicalRecordId) {
        patientIds.put(medicalRecordId, medicalRecordAutoIncrement++);
    }

    public ObjectId getMedicalRecordTrueID (int id) {
        for (Map.Entry<ObjectId, Integer> entry : medicalRecordsIds.entrySet()) {
            if (entry.getValue().equals(id)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void fillDoctorIds (List<Doctor> doctors) {
        if (doctorIds.isEmpty())
            doctors.forEach(d -> patientIds.put(d.get_id(), doctorAutoIncrement++) );
    }

    public void addDoctorId (ObjectId doctorId) {
        doctorIds.put(doctorId, doctorAutoIncrement++);
    }

    public ObjectId getDoctorTrueID (int id) {
        for (Map.Entry<ObjectId, Integer> entry : doctorIds.entrySet()) {
            if (entry.getValue().equals(id)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
