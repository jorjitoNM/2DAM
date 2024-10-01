package com.hospitalcrud.domain.service;


import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.model.Medication;
import com.hospitalcrud.dao.respositories.MedicalRecordRepository;
import com.hospitalcrud.domain.model.MedicalRecordUI;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public int addMedicalRecord(MedicalRecordUI medicalRecordUI) {
        return medicalRecordRepository.addMedicalRecord(new MedicalRecord(medicalRecordUI.getId(),
                medicalRecordUI.getIdPatient(), medicalRecordUI.getIdDoctor(),
                medicalRecordUI.getDescription(), LocalDate.parse(medicalRecordUI.getDate()),null));
                //parseMedications(medicalRecordUI.getMedications())));
    }

    /*private List<Medication> parseMedications(List<String> medications) {
        List<Medication> medicationList = new ArrayList<>();
        medications.forEach(m -> medicationList.add(new Medication()))
        return medicationList;
    }*/

    public List<MedicalRecordUI> getMedicalRecords(int idPatient) {
        List<MedicalRecordUI> medicalRecordsUI = new ArrayList<MedicalRecordUI>();
        medicalRecordRepository.getMedicalRecords(idPatient).forEach(mr ->
                medicalRecordsUI.add(new MedicalRecordUI(mr.getId(), mr.getDiagnosis(), mr.getDate().toString(),
                        mr.getIdPatient(), mr.getIdDoctor(), parseStringMedications(mr.getMedications()))));
        return medicalRecordsUI;
    }

    private List<String> parseStringMedications(List<Medication> medications) {
        List<String> stringMedications = new ArrayList<>();
        medications.forEach(m -> stringMedications.add(m.getMedicationName()));
        return stringMedications;
    }

    public void deleteMedicalRecord(int id) {
        medicalRecordRepository.deleteMedicalRecord(id);
    }

    public void updateMedicalRecord(MedicalRecordUI medicalRecordUI) {
        medicalRecordRepository.updateMedicalRecord(new MedicalRecord(medicalRecordUI.getId(), medicalRecordUI.getIdPatient()
                , medicalRecordUI.getIdDoctor(), medicalRecordUI.getDescription(), LocalDate.parse(medicalRecordUI.getDate()),null));
                //parseMedications(medicalRecordUI.getMedications())));
    }
}
