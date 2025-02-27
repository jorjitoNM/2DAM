package org.examen.domain.service;

import jakarta.inject.Inject;
import org.bson.Document;
import org.examen.dao.repository.mongo.MongoAggregationsRepository;

import java.util.List;

public class MongoAggregationService {
    private final MongoAggregationsRepository repository;

    @Inject
    public MongoAggregationService(MongoAggregationsRepository repository) {
        this.repository = repository;
    }

    public List<Document> query1() {
        return repository.query1();
    }

    public List<Document> query2() {
        return repository.query2();
    }
}
