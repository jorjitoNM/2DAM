package com.hospital_jpa.domain.service;

import com.hospital_jpa.dao.repository.MedicationsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicationsService {

    private final MedicationsRepository medicationsRepository;

    public MedicationsService(MedicationsRepository medicationsRepository) {
        this.medicationsRepository = medicationsRepository;
    }

    public List<String> getAll() {
        List<String> medications = new ArrayList<>();
        medicationsRepository.findAll().forEach(m -> medications.add(m.getMedicationName()));
        return medications;
    }
}
