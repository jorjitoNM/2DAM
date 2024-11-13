package com.hospitalcrud.dao.respositories.spring;

import com.hospitalcrud.dao.mappers.spring_mappers.MapSpringCredential;
import com.hospitalcrud.dao.model.Credential;
import com.hospitalcrud.dao.respositories.CredentialRepository;
import com.hospitalcrud.dao.utilities.SQLQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class SpringCredentialsRepository implements CredentialRepository {
    @Autowired
    private JdbcClient jdbcClient;
    private final MapSpringCredential credentialMapper;


    public SpringCredentialsRepository(MapSpringCredential credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    @Override
    public List<Credential> getAll() {
        return List.of();
    }

    @Override
    public boolean delete(int patientId) {
        jdbcClient.sql(SQLQueries.DELETE_CREDENTIAL).param("id",patientId).query();
        return false;
    }

    @Override
    public void save(Credential credential) {

    }

    @Override
    public void update(Credential credential) {

    }

    @Override
    public Credential get(String username) {
        return jdbcClient.sql(SQLQueries.GET_CREDENTIAL)
                .param(1,username)
                .query(credentialMapper)
                .optional().orElse(null);
    }
}
