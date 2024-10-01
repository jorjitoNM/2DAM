package com.hospitalcrud.dao.respositories;

import com.hospitalcrud.dao.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepository {
    public List<MedicalRecord> getMedicalRecords(int idPatient) {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(new MedicalRecord(1, 1, 1, "Cancer"
                , LocalDate.of(1990, 6, 12)
                , new ArrayList<>()));
        medicalRecords.add(new MedicalRecord(2, 1, 2, "Diarrhoea"
                , LocalDate.of(1992, 9, 7)
                , new ArrayList<>()));
        medicalRecords.add(new MedicalRecord(3, 2, 1, "Cancer"
                , LocalDate.of(2001, 9, 11)
                , new ArrayList<>()));
        medicalRecords.add(new MedicalRecord(4, 3, 3, "Diarrhoea"
                , LocalDate.of(2012, 4, 26)
                , new ArrayList<>()));
        medicalRecords.add(new MedicalRecord(5, 3, 2, "Chlamydia"
                , LocalDate.of(2022, 3, 19)
                , new ArrayList<>()));
        return medicalRecords.stream().filter(mr -> mr.getIdPatient()==idPatient).toList();
    }
    public int addMedicalRecord(MedicalRecord medicalRecord) {
        getMedicalRecords(medicalRecord.getIdPatient()).add(medicalRecord);
        return medicalRecord.getId();

    }

    public void updateMedicalRecord(MedicalRecord medicalRecord) {

    }

    public void deleteMedicalRecord(int id) {

    }
}
