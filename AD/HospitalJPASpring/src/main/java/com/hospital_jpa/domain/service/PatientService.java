package com.hospital_jpa.domain.service;


import com.hospital_jpa.dao.model.Credential;
import com.hospital_jpa.dao.model.Patient;
import com.hospital_jpa.dao.model.Payment;
import com.hospital_jpa.dao.repository.AppointmentsRepository;
import com.hospital_jpa.dao.repository.MedicalRecordsRepository;
import com.hospital_jpa.dao.repository.PatientRepository;
import com.hospital_jpa.dao.repository.PaymentsRepository;
import com.hospital_jpa.domain.error.FOREIGN_KEY_ERROR;
import com.hospital_jpa.domain.model.PatientUI;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class PatientService {

    private final PatientRepository patientRepository;
    private final PaymentsRepository paymentsRepository;
    private final AppointmentsRepository appointmentsRepository;
    private final MedicalRecordsRepository medicalRecordsRepository;

    public PatientService(PatientRepository patientRepository, PaymentsRepository paymentsRepository, AppointmentsRepository appointmentsRepository, MedicalRecordsRepository medicalRecordsRepository, MedicalRecordsRepository medicalRecordsRepository1) {
        this.patientRepository = patientRepository;
        this.paymentsRepository = paymentsRepository;
        this.appointmentsRepository = appointmentsRepository;
        this.medicalRecordsRepository = medicalRecordsRepository1;
    }

    public List<PatientUI> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientUI> patientsUI = new ArrayList<>();
        patients.forEach(p -> patientsUI.add(new PatientUI(p)));
        List<Payment> payments = paymentsRepository.getPaymentsByPatient();
        payments.forEach(p -> patientsUI.stream()
                .filter(patient -> patient.getId() == p.getPatient().getId())
                .findAny()
                .ifPresent(found -> found.setPaid(p.getAmount())));
        return patientsUI;
    }

    public int addPatient(PatientUI patientUI) {
        Patient patient = new Patient(patientUI.getId(), patientUI.getName(), patientUI.getBirthDate(),
                patientUI.getPhone(), new Credential(patientUI.getUserName(), patientUI.getPassword()));
        return patientRepository.save(patient).getId();
    }

    public void updatePatient(PatientUI patientUI) {
        Patient patient = new Patient(patientUI.getId(), patientUI.getName(), patientUI.getBirthDate(), patientUI.getPhone());
        patientRepository.save(patient);
    }


    public void deletePatient(int patientId, boolean confirmation) {
        try {
            if (confirmation) {
                appointmentsRepository.deleteAllByPatient_Id(patientId);
                paymentsRepository.deleteAllByPatient_Id(patientId);
                medicalRecordsRepository.deleteAllByPatient_Id(patientId);
            }
            patientRepository.deleteById(patientId);
        } catch (DataIntegrityViolationException e) {
            throw new FOREIGN_KEY_ERROR();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
