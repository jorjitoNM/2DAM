package com.hospital_jpa.dao.repository;

import com.hospital_jpa.dao.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecord, Integer> {
    List<MedicalRecord> findAllByPatient_Id(Integer patientId);
    void deleteAllByPatient_Id(int patientId);
}
