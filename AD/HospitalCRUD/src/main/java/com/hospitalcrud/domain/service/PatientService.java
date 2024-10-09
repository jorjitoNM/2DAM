package com.hospitalcrud.domain.service;


import com.hospitalcrud.dao.model.Credential;
import com.hospitalcrud.dao.model.Patient;
import com.hospitalcrud.dao.respositories.CredentialRepository;
import com.hospitalcrud.dao.respositories.PatientRepository;
import com.hospitalcrud.domain.model.PatientUI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final CredentialRepository credentialRepository;

    public PatientService(PatientRepository patientRepository, CredentialRepository credentialRepository) {
        this.patientRepository = patientRepository;
        this.credentialRepository = credentialRepository;
    }

    public List<PatientUI> getPatients() {
        List<Patient> patients = patientRepository.getAll();
        List<PatientUI> patientsUI = new ArrayList<>();
        patients.forEach(p -> patientsUI.add(new PatientUI(p)));
        return patientsUI;
    }

    public int addPatient(PatientUI patientUI) {
        Patient patient = new Patient(patientUI.getId(),patientUI.getName(),patientUI.getBirthDate(),patientUI.getPhone());
        int serverID = patientRepository.save(patient);
        Credential credential = new Credential(patientUI.getUserName(),patientUI.getPassword(),serverID);
        credentialRepository.save(credential);
        return serverID;
    }

    public void updatePatient(PatientUI patientUI) {
        Patient patient = new Patient(patientUI.getId(),patientUI.getName(),patientUI.getBirthDate(),patientUI.getPhone());
        patientRepository.update(patient);
    }

    public void deletePatient(int patientId, boolean confirmation) {
        patientRepository.delete(patientId,confirmation);
    }
}
