package com.hospitalcrud.domain.service;

import com.hospitalcrud.dao.model.Doctor;
import com.hospitalcrud.dao.respositories.DoctorsRepository;
import com.hospitalcrud.domain.model.DoctorUI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorsRepository doctorsRepository;

    public DoctorService(DoctorsRepository doctorsRepository) {
        this.doctorsRepository = doctorsRepository;
    }
    public List<DoctorUI> getAll() {
        List<Doctor> doctors = doctorsRepository.getAll();

        return ;
    }
}
