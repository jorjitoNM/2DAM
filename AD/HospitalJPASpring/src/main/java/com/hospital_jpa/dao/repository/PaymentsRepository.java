package com.hospital_jpa.dao.repository;

import com.hospital_jpa.dao.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Integer> {
    @Query(value = "select new Payment(p.patient.id,sum(p.amount)) from Payment p group by p.patient.id")
    List<Payment> getPaymentsByPatient();
}
