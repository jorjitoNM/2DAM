package org.example.appmensajessecretos.dao;


import com.google.gson.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DataBase {
   private final Gson gson;
   public DataBase() {
       gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
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

               .create();
   }
}
