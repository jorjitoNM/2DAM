package org.examen.dao.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.examen.common.Constantes;


public class MongoUtil implements AutoCloseable {

    private final MongoClient mongoClient;
    @Getter
    private final MongoDatabase database;

    public MongoUtil() {
        this.mongoClient = MongoClients.create("mongodb://root:quevedo2dam@localhost:27017/exam?authSource=admin");
        this.database = mongoClient.getDatabase(Constantes.DB_NAME);
    }

    @Override
    public void close() {
        mongoClient.close();
    }
}
