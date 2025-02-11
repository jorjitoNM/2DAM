package com.hospital_jpa.dao.repository;

import com.hospital_jpa.dao.model.MedicalRecord;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecord, Integer> {
    List<MedicalRecord> findAllByPatient_Id(Integer patientId);
    @Transactional(noRollbackFor = DataIntegrityViolationException.class)
    void deleteAllByPatient_Id(int patientId);
}
