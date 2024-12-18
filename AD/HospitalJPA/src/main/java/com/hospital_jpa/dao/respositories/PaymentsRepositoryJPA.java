package com.hospital_jpa.dao.respositories;

import com.hospital_jpa.dao.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentsRepositoryJPA implements com.hospital_jpa.dao.interfaces.PaymentsRepository {
    @Override
    public List<Payment> getAll() {
        return List.of();
    }

    @Override
    public int save(Payment payment) {
        return 0;
    }

    @Override
    public void update(Payment payment) {

    }

    @Override
    public boolean delete(int paymentId) {
        return false;
    }

    @Override
    public List<Payment> getPaymentsByPatient() {
        return List.of();
    }

    @Override
    public void deletePatientPayments(int patientId) {

    }
}
