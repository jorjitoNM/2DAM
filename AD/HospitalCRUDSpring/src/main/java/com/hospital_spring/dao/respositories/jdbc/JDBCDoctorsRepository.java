package com.hospital_spring.dao.respositories.jdbc;

import com.hospital_spring.dao.mappers.jdbc_mappers.MapDoctors;
import com.hospital_spring.dao.model.Doctor;
import com.hospital_spring.dao.respositories.DoctorsRepository;
import com.hospital_spring.dao.utilities.DBConnectionPool;
import com.hospital_spring.dao.utilities.SQLQueries;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
@Profile("jdbc")
public class JDBCDoctorsRepository implements DoctorsRepository {
    private final DBConnectionPool pool;
    private final MapDoctors doctorsMapper;

    public JDBCDoctorsRepository(DBConnectionPool pool, MapDoctors doctorsMapper) {
        this.pool = pool;
        this.doctorsMapper = doctorsMapper;
    }

    @Override
    public List<Doctor> getAll() {
        try (Connection conn = pool.getConnection();
             Statement stmt = conn.createStatement();
        ) {
            return doctorsMapper.mapDoctors(stmt.executeQuery(SQLQueries.GET_ALL_DOCTORS));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
