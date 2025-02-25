package hospitaljpa.dao.respositories.spring;

import com.hospitalcrud.dao.respositories.AppointmentsRepository;
import com.hospitalcrud.dao.utilities.SQLQueriesSpring;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
@Profile("spring")
public class SpringAppointmentsRepository implements AppointmentsRepository {
    private final JdbcClient jdbcClient;

    public SpringAppointmentsRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void delete(int patientId) {
        jdbcClient.sql(SQLQueriesSpring.DELETE_PATIENT_APPOINTMENTS).param("id",patientId).update();
    }
}
