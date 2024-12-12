package org.example.hospitaljpa.dao.respositories.spring;


import com.hospitalcrud.dao.mappers.spring_mappers.MapSpringPayments;
import com.hospitalcrud.dao.model.Payment;
import com.hospitalcrud.dao.respositories.PaymentsRepository;
import com.hospitalcrud.dao.utilities.SQLQueriesSpring;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("spring")
public class SpringPaymentsRepository implements PaymentsRepository {
    private final JdbcClient jdbcClient;
    private final MapSpringPayments paymentsMapper;

    public SpringPaymentsRepository(JdbcClient jdbcClient, MapSpringPayments paymentsMapper) {
        this.jdbcClient = jdbcClient;
        this.paymentsMapper = paymentsMapper;
    }

    public void deletePatientPayments (int patientId) {
        jdbcClient.sql(SQLQueriesSpring.DELETE_PATIENT_PAYMENTS).param("id",patientId).query();
    }

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
        return jdbcClient.sql(SQLQueriesSpring.GET_GROUPED_PAYMENTS).query(paymentsMapper).list();
    }
}
