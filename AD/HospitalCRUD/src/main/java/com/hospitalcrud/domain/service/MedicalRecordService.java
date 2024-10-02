package com.hospitalcrud.domain.service;


import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.model.Medication;
import com.hospitalcrud.dao.respositories.statiC.MedicalRecordRepository;
import com.hospitalcrud.dao.respositories.statiC.MedicationsRepository;
import com.hospitalcrud.domain.model.MedicalRecordUI;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicationsRepository medicationsRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicationsRepository = new MedicationsRepository();
    }

    public int addMedicalRecord(MedicalRecordUI medicalRecordUI) {
        return medicalRecordRepository.save(new MedicalRecord(medicalRecordUI.getId(),
                medicalRecordUI.getIdPatient(), medicalRecordUI.getIdDoctor(),
                medicalRecordUI.getDescription(), LocalDate.parse(medicalRecordUI.getDate()),
                parseMedications(medicalRecordUI.getMedications(),medicalRecordUI.getId())));

    }

    private List<Medication> parseMedications(List<String> medications, int medicalRecordId) {
        List<Medication> medicationList = new ArrayList<>();
        List<Medication> aviableMedications = medicationsRepository.getAll();
        medications.forEach(m -> medicationList.add(
                new Medication(aviableMedications.stream().filter(med ->
                        med.getMedicationName().equals(m)).findFirst().get().getId(),m,medicalRecordId)));
        return medicationList;
    }

    public List<MedicalRecordUI> getMedicalRecords(int idPatient) {
        List<MedicalRecordUI> medicalRecordsUI = new ArrayList<MedicalRecordUI>();
        medicalRecordRepository.getAll(idPatient).forEach(mr ->
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
        medicalRecordRepository.delete(id);
    }

    public void updateMedicalRecord(MedicalRecordUI medicalRecordUI) {
        medicalRecordRepository.update(new MedicalRecord(medicalRecordUI.getId(), medicalRecordUI.getIdPatient()
                , medicalRecordUI.getIdDoctor(), medicalRecordUI.getDescription(), LocalDate.parse(medicalRecordUI.getDate()),parseMedications(medicalRecordUI.getMedications(),medicalRecordUI.getId())));
    }
}
