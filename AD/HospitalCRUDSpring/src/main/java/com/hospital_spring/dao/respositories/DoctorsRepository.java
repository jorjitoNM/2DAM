package com.hospital_spring.dao.respositories;

import com.hospital_spring.dao.model.Doctor;

import java.util.List;

public interface DoctorsRepository {
    List<Doctor> getAll();
}
