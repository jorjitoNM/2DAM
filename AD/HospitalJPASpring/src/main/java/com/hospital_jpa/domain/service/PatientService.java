package com.hospital_jpa.domain.service;


import com.hospital_jpa.dao.model.*;
import com.hospital_jpa.dao.repository.AppointmentsRepository;
import com.hospital_jpa.dao.repository.MedicalRecordsRepository;
import com.hospital_jpa.dao.repository.PatientRepository;
import com.hospital_jpa.dao.repository.PaymentsRepository;
import com.hospital_jpa.domain.error.DUPLICATED_USERNAME;
import com.hospital_jpa.domain.error.FOREIGN_KEY_ERROR;
import com.hospital_jpa.domain.model.PatientUI;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class PatientService {

    private final PatientRepository patientRepository;
    private final PaymentsRepository paymentsRepository;
    private final AppointmentsRepository appointmentsRepository;
    private final MedicalRecordsRepository medicalRecordsRepository;
    private final RoleService roleService;

    public PatientService(PatientRepository patientRepository, PaymentsRepository paymentsRepository, AppointmentsRepository appointmentsRepository, MedicalRecordsRepository medicalRecordsRepository, MedicalRecordsRepository medicalRecordsRepository1, RoleService roleService) {
        this.patientRepository = patientRepository;
        this.paymentsRepository = paymentsRepository;
        this.appointmentsRepository = appointmentsRepository;
        this.medicalRecordsRepository = medicalRecordsRepository1;
        this.roleService = roleService;
    }

    public List<PatientUI> getPatients() {
        FileUser fileUser = roleService.getFileUser();
        List<Patient> patients = patientRepository.findAll();
        if (UserType.PATIENT.equals(fileUser.getUserType()))
            patients = patients.stream().filter(p -> p.getId() == fileUser.getId()).toList();
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
        FileUser fileUser = roleService.getFileUser();
        if (UserType.PATIENT.equals(fileUser.getUserType()))
            return -1;
        else {
            try {
                Patient patient = new Patient(patientUI.getId(), patientUI.getName(), patientUI.getBirthDate(),
                        patientUI.getPhone(), new Credential(patientUI.getUserName(), patientUI.getPassword()));
                return patientRepository.save(patient).getId();
            } catch (DataIntegrityViolationException e) {
                throw new DUPLICATED_USERNAME();
            }
        }
    }

    public void updatePatient(PatientUI patientUI) {
        Patient patient = new Patient(patientUI.getId(), patientUI.getName(), patientUI.getBirthDate(), patientUI.getPhone());
        patientRepository.save(patient);
    }


    public void deletePatient(int patientId, boolean confirmation) {
        FileUser fileUser = roleService.getFileUser();
        if (!UserType.PATIENT.equals(fileUser.getUserType())) {
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
}
