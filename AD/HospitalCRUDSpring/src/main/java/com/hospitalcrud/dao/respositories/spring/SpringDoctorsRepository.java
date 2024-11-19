package com.hospitalcrud.dao.respositories.spring;

import com.hospitalcrud.dao.model.Doctor;
import com.hospitalcrud.dao.respositories.DoctorsRepository;
import com.hospitalcrud.dao.utilities.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("spring")
public class SpringDoctorsRepository implements DoctorsRepository {
    @Autowired
    private JdbcClient jdbcClient;

    @Override
    public List<Doctor> getAll() {
        return jdbcClient.sql(SQLQueries.GET_ALL_DOCTORS).query(Doctor.class).list();
    }
}
