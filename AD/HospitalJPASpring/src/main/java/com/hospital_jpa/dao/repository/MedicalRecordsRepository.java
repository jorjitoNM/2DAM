package com.hospital_jpa.dao.repository;

import com.hospital_jpa.dao.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecord, Integer> {}
