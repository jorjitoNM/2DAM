package com.hospital_jpa.dao.respositories;

import com.google.gson.Gson;
import com.hospital_jpa.dao.common.Constants;
import com.hospital_jpa.dao.model.MedicalRecord;
import com.hospital_jpa.dao.model.Patient;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository
@Log4j2
public class MedicalRecordsRepository implements com.hospital_jpa.dao.interfaces.MedicalRecordsRepository {

    private final Gson gson;

    public MedicalRecordsRepository(Gson gson) {
        this.gson = gson;
    }

    @Override
    public List<MedicalRecord> getAll(ObjectId patientId) {
        try (MongoClient mongo = MongoClients.create(Constants.MONGODB_URL)) {
            MongoDatabase db = mongo.getDatabase(Constants.DB_NAME);
            MongoCollection<Document> est = db.getCollection(Constants.MEDICAL_RECORDS);
            List<MedicalRecord> medicalRecords = new ArrayList<>();
            List<Document> documents = est.find(eq(com.hospital_jpa.common.Constants.PATIENT,patientId)).into(new ArrayList<>());
            for (Document document : documents) {
                MedicalRecord medicalRecord = gson.fromJson(document.toJson(), MedicalRecord.class);
                medicalRecord.set_id(document.getObjectId(com.hospital_jpa.common.Constants.ID));
                medicalRecords.add(medicalRecord);
            }
            return medicalRecords;
        }
    }

    @Override
    public int delete(ObjectId medicalRecordId) {
        try (MongoClient mongo = MongoClients.create(Constants.MONGODB_URL)) {
            MongoDatabase db = mongo.getDatabase(Constants.DB_NAME);
            MongoCollection<Document> est = db.getCollection(Constants.MEDICAL_RECORDS);
            return (int) est.deleteOne(eq(com.hospital_jpa.common.Constants.ID, medicalRecordId)).getDeletedCount();
        }
    }

    @Override
    public ObjectId save(MedicalRecord medicalRecord) {
        return medicalRecord.get_id();
    }

    @Override
    public void update(MedicalRecord medicalRecord) {
    }
}
