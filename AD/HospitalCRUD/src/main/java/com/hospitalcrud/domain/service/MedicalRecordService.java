package com.hospitalcrud.domain.service;


import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.model.MedicalRecords;
import com.hospitalcrud.dao.model.Medication;
import com.hospitalcrud.dao.respositories.MedicalRecordsRepository;
import com.hospitalcrud.dao.respositories.statiC.StaticMedicationsRepository;
import com.hospitalcrud.domain.model.MedicalRecordUI;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService {
    private final MedicalRecordsRepository medicalRecordsRepository;
    private final StaticMedicationsRepository medicationsRepository;

    public MedicalRecordService(MedicalRecordsRepository medicalRecordsRepository) {
        this.medicalRecordsRepository = medicalRecordsRepository;
        this.medicationsRepository = new StaticMedicationsRepository();
    }

    public int addMedicalRecord(MedicalRecordUI medicalRecordUI) {
        return medicalRecordsRepository.save(new MedicalRecord(medicalRecordUI.getId(),
                medicalRecordUI.getIdPatient(), medicalRecordUI.getIdDoctor(),
                medicalRecordUI.getDescription(), LocalDate.parse(medicalRecordUI.getDate()),
                parseMedications(medicalRecordUI.getMedications(),medicalRecordUI.getId())));

    }

    private List<Medication> parseMedications(List<String> medications, int medicalRecordId) {
        List<Medication> medicationList = new ArrayList<>();
        List<Medication> availableMedications = medicationsRepository.getAll();
        medications.forEach(m -> medicationList.add(
                new Medication(availableMedications.stream().filter(med ->
                        med.getMedicationName().equals(m)).findFirst().get().getId(),m,medicalRecordId)));
        return medicationList;
    }

    public List<MedicalRecordUI> getMedicalRecords(int idPatient) {
        List<MedicalRecordUI> medicalRecordsUI = new ArrayList<>();
        medicalRecordsRepository.getAll(idPatient).forEach(mr ->
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
        medicalRecordsRepository.delete(id);
    }

    public void updateMedicalRecord(MedicalRecordUI medicalRecordUI) {
        MedicalRecord medicalRecord = new MedicalRecord(medicalRecordUI.getId(), medicalRecordUI.getIdPatient()
                , medicalRecordUI.getIdDoctor(), medicalRecordUI.getDescription(), LocalDate.parse(medicalRecordUI.getDate()),
                parseMedications(medicalRecordUI.getMedications(),medicalRecordUI.getId()));
        List<MedicalRecord> medicalRecords = medicalRecordsRepository.update(medicalRecord);
        MedicalRecord found = medicalRecords.stream().filter(m -> m.getId() == medicalRecord.getId()).findAny().orElse(null);
        if (found != null) {
            found.setDate(medicalRecord.getDate());
            found.setMedications(medicalRecord.getMedications());
            found.setDiagnosis(medicalRecord.getDiagnosis());
            found.setIdDoctor(medicalRecord.getIdDoctor());
            medicalRecordsRepository.saveMedicalRecords(medicalRecords);
        }
    }
}
