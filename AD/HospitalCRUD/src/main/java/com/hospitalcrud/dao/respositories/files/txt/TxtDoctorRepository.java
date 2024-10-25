package com.hospitalcrud.dao.respositories.files.txt;

import com.hospitalcrud.dao.configuration.FilesConfiguration;
import com.hospitalcrud.dao.mappers.DoctorRowMapper;
import com.hospitalcrud.dao.model.Doctor;
import com.hospitalcrud.dao.respositories.DoctorsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Profile("inDevelopment")
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
        try {
            Files.readAllLines(configuration.getPathDoctors())
                    .forEach(l -> doctors.add(doctorMapper.mapRow(l)));
        } catch (IOException e) {
            //log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
        return doctors;
    }
}
