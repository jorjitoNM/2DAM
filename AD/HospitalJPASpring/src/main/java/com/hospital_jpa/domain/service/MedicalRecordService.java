package com.hospital_jpa.domain.service;


import com.hospital_jpa.dao.model.*;
import com.hospital_jpa.dao.repository.MedicalRecordsRepository;
import com.hospital_jpa.dao.repository.MedicationsRepository;
import com.hospital_jpa.domain.model.MedicalRecordUI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService {
    private final MedicalRecordsRepository medicalRecordsRepository;
    private final MedicationsRepository medicationsRepository;
    private final RoleService roleService;

    public MedicalRecordService(MedicalRecordsRepository medicalRecordsRepository, MedicationsRepository medicationsRepository, RoleService roleService) {
        this.medicalRecordsRepository = medicalRecordsRepository;
        this.medicationsRepository = medicationsRepository;
        this.roleService = roleService;
    }

    public int addMedicalRecord(MedicalRecordUI medicalRecordUI) {
        FileUser fileUser = roleService.getFileUser();
        if (UserType.PATIENT.equals(fileUser.getUserType()))
            return -1;
        else {
            if (UserType.DOCTOR.equals(fileUser.getUserType()))
                medicalRecordUI.setIdDoctor(fileUser.getId());
            MedicalRecord medicalRecord = new MedicalRecord(new Patient(medicalRecordUI.getIdPatient()), medicalRecordUI.getIdDoctor(),
                    medicalRecordUI.getDescription(), LocalDate.parse(medicalRecordUI.getDate()));
            medicalRecord.setMedications(parseMedications(medicalRecordUI.getMedications()));
            medicalRecord.getMedications().forEach(m -> m.setMedicalRecord(medicalRecord));
            return medicalRecordsRepository.save(medicalRecord).getId();
        }
    }

    private List<Medication> parseMedications(List<String> medications) {
        List<Medication> medicationList = new ArrayList<>();
        medications.forEach(medication -> medicationList.add(new Medication(medication, "every 8 hours")));
        return medicationList;
    }

    public List<MedicalRecordUI> getMedicalRecords(int idPatient) {
        FileUser fileUser = roleService.getFileUser();
        List<MedicalRecordUI> medicalRecordsUI = new ArrayList<>();
        List<MedicalRecord> medicalRecords = medicalRecordsRepository.findAllByPatient_Id(idPatient);
        if (UserType.DOCTOR.equals(fileUser.getUserType()))
            medicalRecords = medicalRecords.stream().filter(m -> m.getIdDoctor() == fileUser.getId()).toList();
        medicalRecords.forEach(mr ->
                medicalRecordsUI.add(new MedicalRecordUI(mr.getId(), mr.getDiagnosis(),
                        mr.getDate().toString(),
                        mr.getId(), mr.getIdDoctor(),
                        parseStringMedications(mr.getMedications()))));
        return medicalRecordsUI;
    }

    private List<String> parseStringMedications(List<Medication> medications) {
        List<String> stringMedications = new ArrayList<>();
        medications.forEach(m -> stringMedications.add(m.getMedicationName()));
        return stringMedications;
    }

    public void deleteMedicalRecord(int id) {
        FileUser fileUser = roleService.getFileUser();
        if (!UserType.PATIENT.equals(fileUser.getUserType()))
            medicalRecordsRepository.findById(id).ifPresent(medicalRecordsRepository::delete);
    }

    @Transactional
    public void updateMedicalRecord(MedicalRecordUI medicalRecordUI) {
        FileUser fileUser = roleService.getFileUser();
        if (!UserType.PATIENT.equals(fileUser.getUserType())) {
            medicationsRepository.deleteAllByMedicalRecord_Id(medicalRecordUI.getId());
            medicalRecordsRepository.save(new MedicalRecord(medicalRecordUI.getId(), new Patient(medicalRecordUI.getIdPatient()), medicalRecordUI.getIdDoctor(),
                    medicalRecordUI.getDescription(), LocalDate.parse(medicalRecordUI.getDate()), parseMedicationsWithRecordId(medicalRecordUI, medicalRecordUI.getMedications())));
        }
    }

    private List<Medication> parseMedicationsWithRecordId(MedicalRecordUI medicalRecordUI, List<String> medications) {
        List<Medication> medicationList = new ArrayList<>();
        medications.forEach(medication -> medicationList.add(new Medication(new MedicalRecord(medicalRecordUI.getId()), medication, "every 8 hours")));
        return medicationList;
    }
}
