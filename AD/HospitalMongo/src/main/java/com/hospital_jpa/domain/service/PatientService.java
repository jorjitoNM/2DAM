package com.hospital_jpa.domain.service;


import com.hospital_jpa.dao.interfaces.CredentialRepository;
import com.hospital_jpa.dao.interfaces.PatientRepository;
import com.hospital_jpa.dao.model.Credential;
import com.hospital_jpa.dao.model.Patient;
import com.hospital_jpa.domain.model.PatientUI;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final CredentialRepository credentialRepository;
    private final Map<ObjectId,Integer> ids;
    private Integer autoIncrement;

    public PatientService(PatientRepository patientRepository, CredentialRepository credentialRepository) {
        this.patientRepository = patientRepository;
        this.credentialRepository = credentialRepository;
        this.ids = new HashMap<>();
        this.autoIncrement = 0;
    }

    public List<PatientUI> getPatients() {
        List<Patient> patients = patientRepository.getAll();
        if (ids.isEmpty())
            fillIds(patients);
        return patients.stream().map(p -> p.toPatientUI(ids.get(p.get_id()))).toList();
    }

    private void fillIds (List<Patient> patients) {
        patients.forEach(p -> ids.put(p.get_id(), autoIncrement++) );
    }

    public int addPatient(PatientUI patientUI) {
        ObjectId generatedId = patientRepository.save(patientUI.toPatient());
        if (generatedId != null) {
            credentialRepository.save(new Credential(patientUI.getUserName(),patientUI.getPassword(),generatedId));
            ids.put(generatedId,autoIncrement++);
        }
        return autoIncrement-1;
    }

    public void updatePatient(PatientUI patientUI) {
        patientRepository.update(patientUI.toPatient(getPatientTrueID(patientUI.getId())));
    }

    public void deletePatient(int patientId, boolean confirmation) {
        ObjectId objectId = getPatientTrueID(patientId);
        if (credentialRepository.delete(objectId) == 1)
            patientRepository.delete(objectId,confirmation);
    }

    private ObjectId getPatientTrueID (int id) {
        for (Map.Entry<ObjectId, Integer> entry : ids.entrySet()) {
            if (entry.getValue().equals(id)) {
                return entry.getKey();
            }
        }
        return null;
    }
}