package com.hospital_jpa.dao.repository;

import com.hospital_jpa.dao.model.Appointment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointment, Integer> {
    @Transactional(noRollbackFor = DataIntegrityViolationException.class)
    void deleteAllByPatient_Id (int id);
}
