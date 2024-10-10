package com.hospitalcrud.dao.respositories.statiC;

import com.hospitalcrud.dao.model.Doctor;
import com.hospitalcrud.dao.respositories.DoctorsRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("static")
public class StaticDoctorRepository implements DoctorsRepository {
    public List<Doctor> getAll() {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor(1,"Paco","Dormir"));
        doctors.add(new Doctor(2,"Pedro","Dormir"));
        doctors.add(new Doctor(3,"Perico","Dormir"));
        return doctors;
    }
}
