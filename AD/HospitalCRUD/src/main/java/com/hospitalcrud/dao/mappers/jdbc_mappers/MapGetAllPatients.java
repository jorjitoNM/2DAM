package com.hospitalcrud.dao.mappers.jdbc_mappers;

import com.hospitalcrud.dao.model.Patient;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapGetAllPatients {
    public List<Patient> readRS(ResultSet pacientes, ResultSet paid) {
        List<Patient> patients = new ArrayList<>();
        try {
            while (pacientes.next()) patients.add(new Patient(
                    pacientes.getInt("patient_id"),
                    pacientes.getString("name"),
                    pacientes.getDate("date_of_birth").toLocalDate(),
                    pacientes.getString("phone"),
                    paid.getInt("amount")
            ));
            return patients;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
