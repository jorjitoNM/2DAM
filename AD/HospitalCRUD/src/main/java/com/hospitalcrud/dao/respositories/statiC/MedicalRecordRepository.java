package com.hospitalcrud.dao.respositories.statiC;

import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.model.Medication;
import com.hospitalcrud.dao.respositories.MedicalRecordsDao;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepository implements MedicalRecordsDao {
    @Override
    public List<MedicalRecord> getAll(int idPatient) {
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
        medicalRecords.forEach(mr -> mr.getMedications().add(new Medication(1,"Ibuprofen",0)));
        return medicalRecords.stream().filter(mr -> mr.getIdPatient()==idPatient).toList();
    }
    @Override
    public int save(MedicalRecord medicalRecord) {
        //getAll(medicalRecord.getIdPatient()).add(medicalRecord);
        return medicalRecord.getId();
    }

    @Override
    public void update(MedicalRecord medicalRecord) {

    }

    @Override
    public void delete(int id) {

    }

}
