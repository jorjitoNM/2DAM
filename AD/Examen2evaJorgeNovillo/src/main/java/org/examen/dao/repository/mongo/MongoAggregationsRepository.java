package org.examen.dao.repository.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.examen.common.Constantes;
import org.examen.dao.utils.MongoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;

@Log4j2
public class MongoAggregationsRepository {

    private final MongoUtil mongoUtil;

    @Inject
    public MongoAggregationsRepository(MongoUtil mongoUtil) {
        this.mongoUtil = mongoUtil;
    }

    public List<Document> query2 () {
        MongoDatabase db = mongoUtil.getDatabase();
        MongoCollection<Document> collection = db.getCollection(Constantes.ZOO);
        List<Document> documents = collection.aggregate(
                Arrays.asList(
                        unwind("$animals"),
                        group("$animals.name",sum("sumOfAnimals",1)),
                        sort(descending("sumOfAnimals")),
                        limit(1),
                project(fields(include("_id"))))
        ).into(new ArrayList<>());
        return documents;
    }

    public List<Document> query1() {
        MongoDatabase db = mongoUtil.getDatabase();
        MongoCollection<Document> collection = db.getCollection(Constantes.ZOO);
        List<Document> documents = collection.aggregate(
                Arrays.asList(
                        match(eq("date","2025-02-15")),
                        group(null,sum("sumOfTickets","$visitor.name"))
                )
        ).into(new ArrayList<>());
        return documents;
    }
}
