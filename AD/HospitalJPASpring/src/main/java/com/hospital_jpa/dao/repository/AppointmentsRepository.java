package com.hospital_jpa.dao.repository;

import com.hospital_jpa.dao.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointment, Integer> {
    void deleteAllByPatient_Id (int id);
}
