package com.hospital_jpa.dao.respositories;

import com.google.gson.Gson;
import com.hospital_jpa.dao.common.Constants;
import com.hospital_jpa.dao.model.Patient;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

@Repository
@Log4j2
public class PatientRepository implements com.hospital_jpa.dao.interfaces.PatientRepository {

    private final Gson gson;

    public PatientRepository(Gson gson) {
        this.gson = gson;
    }


    @Override
    public List<Patient> getAll() {
        try (MongoClient mongo = MongoClients.create(Constants.MONGODB_URL)) {
            MongoDatabase db = mongo.getDatabase(Constants.DB_NAME);
            MongoCollection<Document> est = db.getCollection(Constants.PATIENTS);
            List<Patient> patients = new ArrayList<>();
            List<Document> documents = est.find().into(new ArrayList<>());
            for (Document document : documents) {
                Patient patient = gson.fromJson(document.toJson(), Patient.class);
                patient.set_id(document.getObjectId(com.hospital_jpa.common.Constants.ID));
                patients.add(patient);
            }
            return patients;
        }
    }

    @Override
    public ObjectId save(Patient patient) {
        try (MongoClient mongo = MongoClients.create(Constants.MONGODB_URL)) {
            MongoDatabase db = mongo.getDatabase(Constants.DB_NAME);
            MongoCollection<Document> est = db.getCollection(Constants.PATIENTS);
            Patient p = Patient.builder().name(patient.getName())
                    .birthDate(patient.getBirthDate()).phone(patient.getPhone()).payments(patient.getPayments()).build();
            Document document = Document.parse(gson.toJson(p));
            est.insertOne(document);
            return (ObjectId) document.get(com.hospital_jpa.common.Constants.ID);
        }
    }

    @Override
    public void update(Patient patient) {
        try (MongoClient mongo = MongoClients.create(Constants.MONGODB_URL)) {
            MongoDatabase db = mongo.getDatabase(Constants.DB_NAME);
            MongoCollection<Document> est = db.getCollection(Constants.PATIENTS);
            Document filter = new Document(com.hospital_jpa.common.Constants.ID, patient.get_id());
            String birthDateStr = gson.toJson(patient.getBirthDate()).replace("\"", "");
            Bson updates = Updates.combine(
                    Updates.set(com.hospital_jpa.common.Constants.NAME, patient.getName()),
                    Updates.set(com.hospital_jpa.common.Constants.BIRTH_DATE,birthDateStr),
                    Updates.set(com.hospital_jpa.common.Constants.PHONE, patient.getPhone())
            );
            est.updateOne(filter, updates);
        }
    }

    @Override
    public void delete(ObjectId patientId, boolean confirmation) {
        try (MongoClient mongo = MongoClients.create(Constants.MONGODB_URL)) {
            MongoDatabase db = mongo.getDatabase(Constants.DB_NAME);
            MongoCollection<Document> est = db.getCollection(Constants.PATIENTS);
            est.deleteOne(eq(com.hospital_jpa.common.Constants.ID, patientId));
        }
    }
}
