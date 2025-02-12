package com.hospital_jpa.domain.service;


import com.hospital_jpa.dao.interfaces.MedicalRecordsRepository;
import com.hospital_jpa.dao.model.MedicalRecord;
import com.hospital_jpa.domain.mappers.MedicalRecordMappers;
import com.hospital_jpa.domain.model.MedicalRecordUI;
import com.hospital_jpa.domain.utils.IdManager;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService {
    private final MedicalRecordsRepository medicalRecordsRepository;
    private final IdManager idManager;
    private final MedicalRecordMappers mappers;


    public MedicalRecordService(MedicalRecordsRepository medicalRecordsRepository, IdManager idManager, MedicalRecordMappers mappers) {
        this.medicalRecordsRepository = medicalRecordsRepository;
        this.idManager = idManager;
        this.mappers = mappers;
    }

    public int addMedicalRecord(MedicalRecordUI medicalRecordUI) {
        ObjectId generatedId = medicalRecordsRepository.save(mappers.toMedicalRecord(
                medicalRecordUI,
                idManager.getPatientTrueID(medicalRecordUI.getIdPatient()),
                idManager.getDoctorTrueID(medicalRecordUI.getIdDoctor())));
        if (generatedId != null)
            idManager.addMedicalRecordId(generatedId);
        return idManager.getMedicalRecordAutoIncrement()-1;
    }

    public List<MedicalRecordUI> getAll(int patientId) {
        return medicalRecordsRepository.getAll(idManager.getPatientTrueID(patientId))
                .stream()
                .map(mr -> mappers.toMedicalRecordUI(
                        mr,
                        idManager.getMedicalRecordsIds().get(mr.get_id()),
                        patientId,
                        idManager.getMedicalRecordsIds().get(mr.getDoctor())
                        ))
                .toList();
    }

    public void deleteMedicalRecord(int id) {
        medicalRecordsRepository.delete(idManager.getMedicalRecordTrueID(id));
    }

    public void updateMedicalRecord(MedicalRecordUI medicalRecordUI) {
        medicalRecordsRepository.update(mappers.toMedicalRecord()));
    }
}
