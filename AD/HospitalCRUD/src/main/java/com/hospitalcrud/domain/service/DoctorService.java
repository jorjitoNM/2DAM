package com.hospitalcrud.domain.service;

import com.hospitalcrud.dao.respositories.statiC.DoctorRepository;
import com.hospitalcrud.domain.model.DoctorUI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    public List<DoctorUI> getAll() {
        List<DoctorUI> doctors = new ArrayList<>();
        doctorRepository.getAll().forEach(d -> doctors.add(new DoctorUI(d.getId(),d.getName())));
        return doctors;
    }
}
