package com.hospitalcrud.dao.respositories.spring;

import com.hospitalcrud.dao.model.Doctor;
import com.hospitalcrud.dao.respositories.DoctorsRepository;
import com.hospitalcrud.dao.utilities.SQLQueries;
import com.hospitalcrud.dao.utilities.SQLQueriesSpring;
import org.springframework.beans.factory.annotation.Autowired;
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
