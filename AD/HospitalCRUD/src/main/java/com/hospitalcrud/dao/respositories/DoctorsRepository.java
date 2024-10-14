package com.hospitalcrud.dao.respositories;

import com.hospitalcrud.dao.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorsRepository {
    List<Doctor> getAll();
}
