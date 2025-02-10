package org.example.hospitaljpa.dao.respositories.jdbc;

import com.hospitalcrud.dao.mappers.jdbc_mappers.MapPayments;
import com.hospitalcrud.dao.model.Payment;
import com.hospitalcrud.dao.respositories.PaymentsRepository;
import com.hospitalcrud.dao.utilities.DBConnectionPool;
import com.hospitalcrud.dao.utilities.SQLQueries;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
@Profile("jdbc")
public class JDBCPaymentsRepository implements PaymentsRepository {

    private final DBConnectionPool pool;
    private final MapPayments paymentsMapper;

    public JDBCPaymentsRepository(DBConnectionPool pool, MapPayments paymentsMapper) {
        this.pool = pool;
        this.paymentsMapper = paymentsMapper;
    }

    @Override
    public List<Payment> getAll() {
        try (Connection con = pool.getConnection();
             Statement getPayments = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ) {
            ResultSet paid = getPayments.executeQuery(SQLQueries.GET_ALL_PAYMENTS);
            return paymentsMapper.readRS(paid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        try (Connection conn = pool.getConnection();
            Statement getPayments = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ) {
            ResultSet paid = getPayments.executeQuery(SQLQueries.GET_GROUPED_PAYMENTS);
            return paymentsMapper.mapPayments(paid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePatientPayments(int patientId) {

    }
}
