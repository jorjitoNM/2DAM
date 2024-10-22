package com.hospitalcrud.dao.respositories.Files.TXT;

import com.hospitalcrud.dao.configuration.FilesConfiguration;
import com.hospitalcrud.dao.mappers.PatientRowMapper;
import com.hospitalcrud.dao.model.Patient;
import com.hospitalcrud.dao.respositories.PatientRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Profile("inDevelopment")
@Log4j2
@Repository
public class TxtPatientRepository implements PatientRepository {
    private final FilesConfiguration configuration;
    private final PatientRowMapper patientMapper;

    public TxtPatientRepository(PatientRowMapper patientMapper) {
        this.patientMapper = patientMapper;
        this.configuration = FilesConfiguration.getInstance();
    }
    private void calculateID() {
        List<Patient> patients = loadPatients();
        int lastID = patients.get(patients.size() - 1).getId();
        configuration.setID(lastID);
    }

    @Override
    public List<Patient> getAll() {
        calculateID();
        return loadPatients();
    }

    @Override
    public int save(Patient patient) {
        patient.setId(configuration.getLastID()+1);
        try (BufferedWriter bw =  Files.newBufferedWriter(configuration.getPathPatients(),APPEND)) {
            bw.append(patient.toStringFichero());
        }
        catch (IOException e) {
            //log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        } finally {
            configuration.setID(patient.getId());
        }
        return patient.getId();
    }

    @Override
    public void update(Patient patient) {
        List<Patient> patients = loadPatients();
        var foundPatient = patients.stream().filter(p -> p.getId()==patient.getId())
                .findFirst().orElse(null);
        if (foundPatient != null) {
            foundPatient.setName(patient.getName());
            foundPatient.setCredential(patient.getCredential());
            foundPatient.setPhone(patient.getPhone());
            foundPatient.setBirthDate(patient.getBirthDate());
        }
        savePatients(patients);
    }

    @Override
    public boolean delete(int patientId, boolean confirmation) {
        if (!confirmation) {
            List<Patient> patients = loadPatients();
            if (patients.removeIf(p -> p.getId() == patientId))
                return savePatients(patients);
            else
                return false;
        }
        return false;
    }

    private boolean savePatients (List<Patient> patients) {
        try (BufferedWriter bw = Files.newBufferedWriter(configuration.getPathPatients(), TRUNCATE_EXISTING)) {
            patients.forEach(p -> {
                try {
                    bw.write(p.toStringFichero());
                } catch (IOException e) {
                    //log.error(e.getMessage());
                    throw new RuntimeException(e);
                }
            });
            return true;
        } catch (IOException e) {
            //log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }
    private List<Patient> loadPatients () {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(configuration.getPathPatients())) {
            br.lines().forEach(l -> patients.add(patientMapper.mapRow(l)));
        } catch (IOException e) {
            //log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
        return patients;
    }
}
