package com.hospitalcrud.dao.respositories;

import com.hospitalcrud.dao.model.Patient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientRepostory {


    public List<Patient> getAll() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(1,"Juan el One", LocalDate.of(1999,05,12),"111-222-333"));
        patients.add(new Patient(2,"Aitor el Mejor", LocalDate.of(1998,10,4),"222-333-444"));
        patients.add(new Patient(3,"Mario el Calvario", LocalDate.of(2001,02,23),"333-444-555"));
        return patients;
    }
    public int addPatient (Patient patient) {
        getAll().add(patient);
        return 4;
    }

    public void updatePatient(Patient patient) {
        getAll().stream().filter(p -> p.getId()==patient.getId()).findFirst().ifPresent(p -> {
            p.setBirthDate(patient.getBirthDate());
            p.setName(patient.getName());
            p.setCredential(patient.getCredential());
            p.setPhone(patient.getPhone());
        });
    }

    public void deletePatient(int patientId, boolean confirmation) {
        if (!confirmation)
            getAll().removeIf(p -> p.getId()==patientId);
    }
}
