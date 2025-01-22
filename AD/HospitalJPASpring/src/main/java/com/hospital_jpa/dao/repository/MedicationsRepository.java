package com.hospital_jpa.dao.repository;

import com.hospital_jpa.dao.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationsRepository extends JpaRepository<Medication, Integer> {
    @Query("SELECT DISTINCT m.medicationName FROM Medication m")
    List<String> findAllDistinctNames ();
    void deleteAllByMedicalRecord_Id (Integer medicalRecordId);
}