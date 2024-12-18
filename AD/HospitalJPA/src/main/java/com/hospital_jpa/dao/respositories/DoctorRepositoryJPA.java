package com.hospital_jpa.dao.respositories;

import com.hospital_jpa.dao.interfaces.DoctorsRepository;
import com.hospital_jpa.dao.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorRepositoryJPA implements DoctorsRepository {
    @Override
    public List<Doctor> getAll() {
        return List.of();
    }
}
