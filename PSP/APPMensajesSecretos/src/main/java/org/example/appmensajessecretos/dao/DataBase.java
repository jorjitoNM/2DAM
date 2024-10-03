package org.example.appmensajessecretos.dao;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Mensaje;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
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
   public List<Usuario> loadUsers () {
        Type userListType = new TypeToken<ArrayList<Usuario>> () {}.getType();
        List<Usuario> users = null;
        try {
            users = gson.fromJson(
                    new FileReader("data/usuarios.json"),userListType
            );
            if (users == null)
                users = new ArrayList<>();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(),e);
        }
       return users;
   }
   public boolean saveUsers (List<Usuario> users) {
       try (FileWriter fw = new FileWriter("data/usuarios.json")) {
           gson.toJson(users, fw);
       } catch (IOException e) {
           log.error(e.getMessage(),e);
           return false;
       }
       return true;
   }

    public List<Mensaje> loadMessages() {
        Type messageListType = new TypeToken<ArrayList<Mensaje>> () {}.getType();
        List<Mensaje> messages = null;
        try {
            messages = gson.fromJson(
                    new FileReader("data/messages.json"), messageListType
            );
            if (messages == null)
                messages = new ArrayList<>();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(),e);
        }
        return messages;
    }

    public boolean saveMessages(List<Mensaje> mensajes) {
       try (FileWriter fw = new FileWriter("data/messages.json")) {
           gson.toJson(mensajes,fw);
       } catch (IOException e) {
           log.error(e.getMessage(),e);
           return true;
       }
       return true;
    }
    public List<Grupo> loadGroups() {
        Type groupListType = new TypeToken<ArrayList<Grupo>> () {}.getType();
        List<Grupo> groups = null;
        try {
            groups = gson.fromJson(
                    new FileReader("data/groups.json"), groupListType
            );
            if (groups == null)
                groups = new ArrayList<>();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(),e);
        }
        return groups;
    }

    public boolean saveGroups(List<Grupo> groups) {
        try (FileWriter fw = new FileWriter("data/groups.json")) {
            gson.toJson(groups,fw);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return true;
        }
        return true;
    }
}
