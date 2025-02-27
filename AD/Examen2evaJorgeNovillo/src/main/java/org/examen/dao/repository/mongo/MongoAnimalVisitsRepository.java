package org.examen.dao.repository.mongo;

import com.google.gson.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import jakarta.inject.Inject;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.examen.common.Constantes;
import org.examen.dao.model.MongoAnimal;
import org.examen.dao.model.MongoAnimalVisit;
import org.examen.dao.model.MongoVisitor;
import org.examen.dao.utils.MongoUtil;
import org.examen.di.ObjectIdTypeAdapter;
import org.examen.domain.errors.AppError;
import org.examen.domain.model.AnimalVisit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.eq;

public class MongoAnimalVisitsRepository {
    private final Gson gson;
    private final MongoUtil mongoUtil;

    @Inject
    public MongoAnimalVisitsRepository(MongoUtil mongoUtil) {
        this.mongoUtil = mongoUtil;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
                                LocalDateTime.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) ->
                                new JsonPrimitive(localDateTime.toString()))
                .registerTypeAdapter(LocalDate.class,
                        (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) ->
                                LocalDate.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(LocalDate.class,
                        (JsonSerializer<LocalDate>) (localDateTime, type, jsonSerializationContext) ->
                                new JsonPrimitive(localDateTime.toString()))
                .registerTypeAdapter(ObjectId.class, new ObjectIdTypeAdapter())
                .create();
    }

    public void update(String visitorName, String animalName, LocalDate date) {
        MongoDatabase db = mongoUtil.getDatabase();
        MongoCollection<Document> collection = db.getCollection(Constantes.ZOO);
        Document document = collection.aggregate(Arrays.asList(
                match(eq("visitor.name", visitorName)))
        ).first();
        if (document == null) {
            throw new AppError("Not found");
        }
        Bson updates = Updates.combine(
                Updates.addToSet("animals",new MongoAnimal(animalName,"Pez"))
        );
        collection.updateOne(document, updates);
    }

    public void save(List<AnimalVisit> visits) {
        MongoDatabase db = mongoUtil.getDatabase();
        MongoCollection<Document> collection = db.getCollection(Constantes.ZOO);
        List<MongoAnimal> mongoAnimals = new ArrayList<>();
        visits.forEach(v -> mongoAnimals.add(MongoAnimal.builder().name(v.getAnimal().getName()).description(v.getAnimal().getSpecies()).build()));
        visits.forEach(v -> {
            MongoAnimalVisit mongoAnimalVisit = MongoAnimalVisit.builder()
                    .date(v.getVisitDate())
                    .animals(mongoAnimals)
                    .visitor(MongoVisitor.builder().name(v.getVisitor().getName()).numTickets(1).build())
                    .build();
            Document document = Document.parse(gson.toJson(mongoAnimalVisit));
            collection.insertOne(document);
        });
    }
}
