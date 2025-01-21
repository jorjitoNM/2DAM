package com.hospital_jpa.dao.repository;

import com.hospital_jpa.dao.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationsRepository extends JpaRepository<Medication, Integer> {}