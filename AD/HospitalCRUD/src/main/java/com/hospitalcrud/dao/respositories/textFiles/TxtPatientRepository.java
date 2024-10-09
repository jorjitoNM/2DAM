package com.hospitalcrud.dao.respositories.textFiles;

import com.hospitalcrud.dao.configuration.FilesConfiguration;
import com.hospitalcrud.dao.mappers.PatientRowMapper;
import com.hospitalcrud.dao.model.Patient;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Repository
public class TxtPatientRepository implements com.hospitalcrud.dao.respositories.PatientRepository {
    private final FilesConfiguration configuration;
    private final PatientRowMapper patientMapper;

    public TxtPatientRepository(PatientRowMapper patientMapper) {
        this.patientMapper = patientMapper;
        this.configuration = FilesConfiguration.getInstance();
    }

    @Override
    public List<Patient> getAll() {
        return loadPatients();
    }

    @Override
    public int save(Patient patient) {
        try {
            OpenOption option = APPEND;
            BufferedWriter bw = Files.newBufferedWriter(Paths.get(configuration.getPathPatients()),option);
            bw.append(patient.toStringFichero());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
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
    public void delete(int patientId, boolean confirmation) {
        if (confirmation) {
            List<Patient> patients = loadPatients();
            patients.remove(patientId);
            savePatients(patients);
        }
    }

    private boolean savePatients (List<Patient> patients) {
        BufferedWriter bw = null;
        try {
            OpenOption option = TRUNCATE_EXISTING;
            bw = Files.newBufferedWriter(Paths.get(configuration.getPathPatients()),option);
            BufferedWriter finalBw = bw;
            patients.forEach(p -> finalBw.write(p.toStringFichero()));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw != null)
                bw.close();
        }
        return false;
    }
    private List<Patient> loadPatients () {
        List<Patient> patients = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = Files.newBufferedReader(Paths.get(configuration.getPathPatients()));
            br.lines().forEach(l -> patients.add(patientMapper.mapRow(l)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }
}
