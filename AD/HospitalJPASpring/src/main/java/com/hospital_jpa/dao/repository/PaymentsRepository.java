package com.hospital_jpa.dao.repository;

import com.hospital_jpa.dao.model.Payment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Integer> {
    @Query(value = "select new Payment(p.patient.id,sum(p.amount)) from Payment p group by p.patient.id")
    List<Payment> getPaymentsByPatient();
    @Transactional(noRollbackFor = DataIntegrityViolationException.class)
    void deleteAllByPatient_Id(int patientId);
}
