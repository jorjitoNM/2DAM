package com.hospitalcrud.dao.respositories;

import com.hospitalcrud.dao.model.Doctor;

import java.util.List;

public interface DoctorsDao {
    List<Doctor> getAll();
}
