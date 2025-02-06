package com.hospital_jpa.dao.respositories;

import com.hospital_jpa.dao.interfaces.DoctorsRepository;
import com.hospital_jpa.dao.model.Doctor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Log4j2
public class DoctorRepository implements DoctorsRepository {

    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctors = new ArrayList<>();
        return doctors;
    }
}
