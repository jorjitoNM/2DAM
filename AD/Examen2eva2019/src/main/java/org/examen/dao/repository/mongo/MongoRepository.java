package org.examen.dao.repository.mongo;

import com.google.gson.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.examen.common.Constantes;
import org.examen.dao.mappers.FactionMapper;
import org.examen.dao.model.FactionMongo;
import org.examen.dao.model.FactionsDocument;
import org.examen.dao.utils.JPAUtil;
import org.examen.dao.utils.MongoUtil;
import org.examen.di.ObjectIdTypeAdapter;
import org.examen.domain.model.Faction;
import org.examen.domain.model.Weapon;
import org.examen.domain.model.WeaponsFaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MongoRepository {

    private final Gson gson;
    private final MongoUtil mongoUtil;

    @Inject
    public MongoRepository(MongoUtil mongoUtil) {
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


    public List<FactionsDocument> loadDataFromMongo() {
        MongoDatabase db = mongoUtil.getDatabase();
        MongoCollection<Document> collection = db.getCollection(Constantes.FACTIONS);
        return  collection.find().map(document -> gson.fromJson(document.toJson(), FactionsDocument.class)).into(new ArrayList<>());
    }
}
