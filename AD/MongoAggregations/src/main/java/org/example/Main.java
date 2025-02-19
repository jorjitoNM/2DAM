package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Aggregates.limit;
import static com.mongodb.client.model.Aggregates.sort;
import static com.mongodb.client.model.Filters.eq;

public class Main {
    public static void main(String[] args) {
        try (MongoClient mongo = MongoClients.create(Constants.MONGODB_URL)) {
            MongoDatabase db = mongo.getDatabase(Constants.DB_NAME);
            MongoCollection<Document> collection = db.getCollection(Constants.PATIENTS);
            collection.aggregate(
                    Arrays.asList(
                    sort(eq(Constants.BIRTH_DATE,1)),
                    limit(1)
            )).into(new ArrayList<>()).forEach(document -> System.out.println(document));
        }
    }
}