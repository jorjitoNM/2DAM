package com.hospital_jpa.di;

import com.google.gson.*;
import org.bson.types.ObjectId;

import java.lang.reflect.Type;

public class ObjectIdTypeAdapter implements JsonSerializer<ObjectId>, JsonDeserializer<ObjectId> {
    @Override
    public JsonElement serialize(ObjectId src, Type typeOfSrc, JsonSerializationContext context) {
        // Serialize ObjectId to a JSON string
        return new JsonPrimitive(src.toHexString());
    }

    @Override
    public ObjectId deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // Handle both string and object representations of ObjectId
        if (json.isJsonPrimitive()) {
            // Case 1: ObjectId is represented as a string (e.g., "507f1f77bcf86cd799439011")
            return new ObjectId(json.getAsString());
        } else if (json.isJsonObject()) {
            // Case 2: ObjectId is represented as an object (e.g., {"$oid": "507f1f77bcf86cd799439011"})
            JsonObject jsonObject = json.getAsJsonObject();
            if (jsonObject.has("$oid")) {
                return new ObjectId(jsonObject.get("$oid").getAsString());
            }
        }
        // Handle unexpected JSON structure
        throw new JsonParseException("Invalid ObjectId format: " + json);
    }
}