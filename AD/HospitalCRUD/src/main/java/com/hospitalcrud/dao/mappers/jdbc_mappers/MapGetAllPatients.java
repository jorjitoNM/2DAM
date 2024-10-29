package com.hospitalcrud.dao.mappers.jdbc_mappers;

import com.hospitalcrud.dao.model.Patient;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapGetAllPatients {
    public List<Patient> readRS(ResultSet rs) {
        List<Patient> patients = new ArrayList<>();
        try {
            while (rs.next()) patients.add(new Patient(
                    rs.getInt("patient_id"),
                    rs.getString("name"),
                    rs.getDate("date_of_birth").toLocalDate(),
                    rs.getString("phone")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }
}
