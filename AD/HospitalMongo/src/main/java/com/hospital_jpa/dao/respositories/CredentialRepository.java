package com.hospital_jpa.dao.respositories;

import com.google.gson.Gson;
import com.hospital_jpa.dao.common.Constants;
import com.hospital_jpa.dao.model.Credential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

import static com.mongodb.client.model.Filters.eq;

@Repository
@Log4j2
public class CredentialRepository implements com.hospital_jpa.dao.interfaces.CredentialRepository {

    private final Gson gson;

    public CredentialRepository(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void save(Credential credential) {
        try (MongoClient mongo = MongoClients.create(Constants.MONGODB_URL)) {
            MongoDatabase db = mongo.getDatabase(Constants.DB_NAME);
            MongoCollection<Document> est = db.getCollection(Constants.CREDENTIAL);
            Credential c = Credential.builder()
                    .username(credential.getUsername())
                    .password(credential.getPassword())
                    .patient(credential.getPatient())
                    .doctor(credential.getDoctor())
                    .build();
            Document document = Document.parse(gson.toJson(c));
            est.insertOne(document);
        }
    }

    @Override
    public int delete(ObjectId patientId) {
        try (MongoClient mongo = MongoClients.create(Constants.MONGODB_URL)) {
            MongoDatabase db = mongo.getDatabase(Constants.DB_NAME);
            MongoCollection<Document> est = db.getCollection(Constants.CREDENTIAL);
            return (int) est.deleteOne(eq(com.hospital_jpa.common.Constants.ID, patientId)).getDeletedCount();
        }
    }

    @Override
    public void update(Credential credential) {

    }

    @Override
    public Credential get(String username) {
        try (MongoClient mongo = MongoClients.create(Constants.MONGODB_URL)) {
            MongoDatabase db = mongo.getDatabase(Constants.DB_NAME);
            MongoCollection<Document> est = db.getCollection(Constants.CREDENTIAL);
            Document document = est.find(eq(com.hospital_jpa.common.Constants.USERNAME,username)).first();
            if (document != null) {
                return gson.fromJson(document.toJson(), Credential.class);
            } else {
                throw new NoSuchElementException("No patient found in the collection.");
            }
        }
    }
}
