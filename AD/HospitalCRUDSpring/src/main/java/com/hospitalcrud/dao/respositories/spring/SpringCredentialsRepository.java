package com.hospitalcrud.dao.respositories.spring;

import com.hospitalcrud.dao.mappers.spring_mappers.MapSpringCredential;
import com.hospitalcrud.dao.model.Credential;
import com.hospitalcrud.dao.model.Patient;
import com.hospitalcrud.dao.respositories.CredentialRepository;
import com.hospitalcrud.dao.utilities.SQLQueriesSpring;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("spring")
public class SpringCredentialsRepository implements CredentialRepository {
    private final JdbcClient jdbcClient;
    private final MapSpringCredential credentialMapper;


    public SpringCredentialsRepository(JdbcClient jdbcClient, MapSpringCredential credentialMapper) {
        this.jdbcClient = jdbcClient;
        this.credentialMapper = credentialMapper;
    }

    @Override
    public List<Credential> getAll() {
        return List.of();
    }

    @Override
    public boolean delete(int patientId) {
        jdbcClient.sql(SQLQueriesSpring.DELETE_CREDENTIAL).param("id",patientId).update();
        return false;
    }

    @Override
    public void save(Patient patient) {
        jdbcClient.sql(SQLQueriesSpring.INSERT_CREDENTIAL)
                .param("username",patient.getCredential().getUserName())
                .param("password",patient.getCredential().getPassword())
                .param("patient_id",patient.getId())
                .param("doctor_id",null)
                .update();
    }

    @Override
    public void update(Credential credential) {

    }

    @Override
    public Credential get(String username) {
        return jdbcClient.sql(SQLQueriesSpring.GET_CREDENTIAL)
                .param("username",username)
                .query(credentialMapper)
                .optional().orElse(null);
    }
}
