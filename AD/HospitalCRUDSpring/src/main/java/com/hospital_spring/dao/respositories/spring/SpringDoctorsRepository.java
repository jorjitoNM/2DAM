package com.hospital_spring.dao.respositories.spring;

import com.hospital_spring.dao.model.Doctor;
import com.hospital_spring.dao.respositories.DoctorsRepository;
import com.hospital_spring.dao.utilities.SQLQueriesSpring;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("spring")
public class SpringDoctorsRepository implements DoctorsRepository {
    private final JdbcClient jdbcClient;

    public SpringDoctorsRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Doctor> getAll() {
        return jdbcClient.sql(SQLQueriesSpring.GET_ALL_DOCTORS).query(Doctor.class).list();
    }
}
