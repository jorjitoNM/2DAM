package com.hospitalcrud.dao.respositories.statiC;

import com.hospitalcrud.dao.model.Doctor;
import com.hospitalcrud.dao.respositories.DoctorsDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorRepository implements DoctorsDao {
    public List<Doctor> getAll() {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor(1,"Paco"));
        doctors.add(new Doctor(2,"Pedro"));
        doctors.add(new Doctor(3,"Perico"));
        return doctors;
    }
}
