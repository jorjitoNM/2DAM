package com.hospitalcrud.dao.respositories.statiC;

import com.hospitalcrud.dao.model.Patient;
import com.hospitalcrud.dao.respositories.PatientRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StaticPatientRepository implements PatientRepository {


    public List<Patient> getAll() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(1,"Juan el One", LocalDate.of(1999,05,12),"111-222-333"));
        patients.add(new Patient(2,"Aitor el Mejor", LocalDate.of(1998,10,4),"222-333-444"));
        patients.add(new Patient(3,"Mario el Calvario", LocalDate.of(2001,02,23),"333-444-555"));
        return patients;
    }

    @Override
    public int save (Patient patient) {
        return 0;
    }

    public int addPatient (Patient patient) {
        getAll().add(patient);
        return 4;
    }

    @Override
    public void update (Patient patient) {
        getAll().stream().filter(p -> p.getId()==patient.getId()).findFirst().ifPresent(p -> {
            p.setBirthDate(patient.getBirthDate());
            p.setName(patient.getName());
            p.setCredential(patient.getCredential());
            p.setPhone(patient.getPhone());
        });
    }

    @Override
    public void delete (int patientId, boolean confirmation) {
        getAll().removeIf(p -> p.getId()==patientId);
    }
}
