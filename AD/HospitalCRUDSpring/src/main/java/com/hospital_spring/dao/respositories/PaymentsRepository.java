package com.hospital_spring.dao.respositories;

import com.hospital_spring.dao.model.Payment;

import java.util.List;

public interface PaymentsRepository {
    List<Payment> getAll();
    int save(Payment payment);
    void update(Payment payment);
    boolean delete(int paymentId);
    List<Payment> getPaymentsByPatient();
    void deletePatientPayments(int patientId);
}
