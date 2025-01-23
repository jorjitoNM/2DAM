package com.hospital_jpa.dao.repository;

import com.hospital_jpa.dao.model.Patient;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Transactional(noRollbackFor = DataIntegrityViolationException.class)
    void deleteById(int id);
}
