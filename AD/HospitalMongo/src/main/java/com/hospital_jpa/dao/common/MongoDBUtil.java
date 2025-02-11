package com.hospital_jpa.dao.common;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Component;

@Component
public class MongoDBUtil {
    private final MongoClient mongoClient;

    public MongoDBUtil(MongoClient mongoClient) {
        this.mongoClient = MongoClients.create(Constants.MONGODB_URL);
    }

    public MongoDatabase getDatabase() {
       return mongoClient.getDatabase(Constants.DB_NAME);
    }
}
