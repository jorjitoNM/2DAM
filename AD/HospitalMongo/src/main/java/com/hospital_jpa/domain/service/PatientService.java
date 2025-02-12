package com.hospital_jpa.domain.service;


import com.hospital_jpa.dao.interfaces.CredentialRepository;
import com.hospital_jpa.dao.interfaces.PatientRepository;
import com.hospital_jpa.dao.model.Credential;
import com.hospital_jpa.dao.model.Patient;
import com.hospital_jpa.domain.mappers.PatientMappers;
import com.hospital_jpa.domain.model.PatientUI;
import com.hospital_jpa.domain.utils.IdManager;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final CredentialRepository credentialRepository;
    private final IdManager idManager;
    private final PatientMappers mappers;

    public PatientService(PatientRepository patientRepository, CredentialRepository credentialRepository, IdManager idManager, PatientMappers mappers) {
        this.patientRepository = patientRepository;
        this.credentialRepository = credentialRepository;
        this.idManager = idManager;
        this.mappers = mappers;
    }

    public List<PatientUI> getAll() {
        List<Patient> patients = patientRepository.getAll();
        idManager.fillPatientIds(patients);
        return patients.stream().map(p -> mappers.toPatientUI(p,idManager.getPatientIds().get(p.get_id()))).toList();
    }

    public int addPatient(PatientUI patientUI) {
        ObjectId generatedId = patientRepository.save(mappers.toPatient(patientUI));
        if (generatedId != null) {
            credentialRepository.save(new Credential(patientUI.getUserName(),patientUI.getPassword(),generatedId));
            idManager.addPatientId(generatedId);
        }
        return idManager.getPatientAutoIncrement()-1;
    }

    public void updatePatient(PatientUI patientUI) {
        patientRepository.update(mappers.toPatient(patientUI,idManager.getPatientTrueID(patientUI.getId())));
    }

    public void deletePatient(int patientId, boolean confirmation) {
        ObjectId objectId = idManager.getPatientTrueID(patientId);
        if (credentialRepository.delete(objectId) == 1)
            patientRepository.delete(objectId,confirmation);
    }
}