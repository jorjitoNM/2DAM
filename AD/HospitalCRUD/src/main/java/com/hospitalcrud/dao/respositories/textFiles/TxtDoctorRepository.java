package com.hospitalcrud.dao.respositories.textFiles;

import com.hospitalcrud.dao.configuration.FilesConfiguration;
import com.hospitalcrud.dao.mappers.DoctorRowMapper;
import com.hospitalcrud.dao.model.Doctor;
import com.hospitalcrud.dao.respositories.DoctorsRepository;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TxtDoctorRepository implements DoctorsRepository {
    private final FilesConfiguration configuration;
    private final DoctorRowMapper doctorMapper;

    public TxtDoctorRepository(DoctorRowMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
        this.configuration = FilesConfiguration.getInstance();
    }
    @Override
    public List<Doctor> getAll() {
        return loadDoctors();
    }
    private List<Doctor> loadDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = Files.newBufferedReader(Paths.get(configuration.getPathDoctors()));
            br.lines().forEach(l -> doctors.add(doctorMapper.mapRow(l)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return doctors;
    }
}
