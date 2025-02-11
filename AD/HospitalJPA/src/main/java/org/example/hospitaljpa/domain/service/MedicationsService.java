package com.hospitalcrud.domain.service;

import com.hospitalcrud.dao.model.Medication;
import com.hospitalcrud.dao.respositories.MedicationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationsService {

    private final MedicationsRepository medicationsRepository;

    public MedicationsService(MedicationsRepository medicationsRepository) {
        this.medicationsRepository = medicationsRepository;
    }

    public List<String> getAll() {
        return medicationsRepository.getAll();
    }
}
