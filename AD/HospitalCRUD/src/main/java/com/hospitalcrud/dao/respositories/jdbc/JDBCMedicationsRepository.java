package com.hospitalcrud.dao.respositories.jdbc;

import com.hospitalcrud.dao.mappers.jdbc_mappers.MapMedications;
import com.hospitalcrud.dao.model.Medication;
import com.hospitalcrud.dao.respositories.MedicationsRepository;
import com.hospitalcrud.dao.utilities.DBConnectionPool;
import com.hospitalcrud.dao.utilities.SQLQueries;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Profile("inDevelopment")
@Repository
public class JDBCMedicationsRepository implements MedicationsRepository {

    private final DBConnectionPool pool;
    private final MapMedications medicationsMapper;

    public JDBCMedicationsRepository(DBConnectionPool pool, MapMedications medicationsMapper) {
        this.pool = pool;
        this.medicationsMapper = medicationsMapper;
    }

    public List<Medication> getPrescribedMedications (int medicalRecordId) {
        try (Connection conn = pool.getConnection()) {
            PreparedStatement getPrescribedMedications = conn.prepareStatement(SQLQueries.GET_PRESCRIBED_MEDICATIONS);
            getPrescribedMedications.setInt(1, medicalRecordId);
            ResultSet rs = getPrescribedMedications.executeQuery();
            return medicationsMapper.readRS(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
