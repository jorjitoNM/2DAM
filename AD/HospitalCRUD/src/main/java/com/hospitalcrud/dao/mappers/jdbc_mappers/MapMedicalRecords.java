package com.hospitalcrud.dao.mappers.jdbc_mappers;

import com.hospitalcrud.dao.model.MedicalRecord;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapMedicalRecords {
    public List<MedicalRecord> readRS (ResultSet rs) {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        try {
            while (rs.next()) medicalRecords.add(new MedicalRecord(
                    rs.getInt("record_id"),
                    rs.getInt("patient_id"),
                    rs.getInt("doctor_id"),
                    rs.getString("diagnosis"),
                    rs.getDate("admission_Date").toLocalDate()
            ));
            return medicalRecords;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
