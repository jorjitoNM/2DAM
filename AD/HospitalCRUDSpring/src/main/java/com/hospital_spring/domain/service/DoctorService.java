package com.hospital_spring.domain.service;

import com.hospital_spring.dao.respositories.DoctorsRepository;
import com.hospital_spring.domain.model.DoctorUI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorsRepository doctorsRepository;

    public DoctorService(DoctorsRepository doctorsRepository) {
        this.doctorsRepository = doctorsRepository;
    }
    public List<DoctorUI> getAll() {
        List<DoctorUI> doctors = new ArrayList<>();
        doctorsRepository.getAll().forEach(d -> doctors.add(new DoctorUI(d.getDoctor_id(),d.getName())));
        return doctors;
    }
}
