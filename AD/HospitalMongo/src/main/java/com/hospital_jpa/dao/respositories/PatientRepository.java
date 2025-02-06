package com.hospital_jpa.dao.respositories;

import com.google.gson.Gson;
import com.hospital_jpa.dao.common.Constants;
import com.hospital_jpa.dao.common.MongoDBUtil;
import com.hospital_jpa.dao.model.Patient;
import com.mongodb.client.MongoCollection;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Log4j2
public class PatientRepository implements com.hospital_jpa.dao.interfaces.PatientRepository {

    private final Gson gson;
    private final MongoDBUtil mongoDBUtil;

    public PatientRepository(Gson gson, MongoDBUtil mongoDBUtil) {
        this.gson = gson;
        this.mongoDBUtil = mongoDBUtil;
    }

    @Override
    public List<Patient> getAll() {
        MongoCollection<Document> est = mongoDBUtil.getDatabase().getCollection(Constants.PATIENTS);
        List<Patient> patients = new ArrayList<>();
        List<Document> documents = est.find().into(new ArrayList<>());
        for (Document supplier : documents) {
            patients.add(gson.fromJson(supplier.toJson(), Patient.class));
        }
        return patients;
    }

    @Override
    public int save(Patient patient) {

        return patient.getId();
    }

    @Override
    public void update(Patient patient) {

    }

    @Override
    public void delete(int patientId, boolean confirmation) {

    }
}
