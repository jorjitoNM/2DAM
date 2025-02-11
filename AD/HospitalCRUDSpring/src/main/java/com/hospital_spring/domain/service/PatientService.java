package com.hospital_spring.domain.service;


import com.hospital_spring.dao.model.Credential;
import com.hospital_spring.dao.model.Patient;
import com.hospital_spring.dao.model.Payment;
import com.hospital_spring.dao.respositories.PatientRepository;
import com.hospital_spring.dao.respositories.PaymentsRepository;
import com.hospital_spring.domain.model.PatientUI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PaymentsRepository paymentsRepository;

    public PatientService(PatientRepository patientRepository, PaymentsRepository paymentsRepository) {
        this.patientRepository = patientRepository;
        this.paymentsRepository = paymentsRepository;
    }

    public List<PatientUI> getPatients() {
        List<Patient> patients = patientRepository.getAll();
        List<Payment> payments = paymentsRepository.getPaymentsByPatient();
        payments.forEach(p -> patients.stream()
                .filter(patient -> patient.getId() == p.getPatientId())
                .findAny()
                .ifPresent(found -> found.setPaid(p.getAmount())));
        List<PatientUI> patientsUI = new ArrayList<>();
        patients.forEach(p -> patientsUI.add(new PatientUI(p)));
        return patientsUI;
    }

    public int addPatient(PatientUI patientUI) {
        Patient patient = new Patient(patientUI.getId(), patientUI.getName(), patientUI.getBirthDate(),
                patientUI.getPhone(), new Credential(patientUI.getUserName(), patientUI.getPassword()));
        return patientRepository.save(patient);
    }

    public void updatePatient(PatientUI patientUI) {
        Patient patient = new Patient(patientUI.getId(), patientUI.getName(), patientUI.getBirthDate(), patientUI.getPhone());
        patientRepository.update(patient);
    }

    public void deletePatient(int patientId, boolean confirmation) {
        patientRepository.delete(patientId,confirmation);
    }
}
