package org.example.appmensajessecretos.dao;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import org.example.appmensajessecretos.config.ConfigurationFicheros;
import org.example.appmensajessecretos.domain.error.DataBaseError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Mensaje;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class DataBase {

   private final Gson gson;

    private final ConfigurationFicheros configuration;

   public DataBase(Gson gson, ConfigurationFicheros configuration) {
       this.gson = gson;
       this.configuration = configuration;
   }

   public Either<Error,List<Usuario>> loadUsers () {
        Type userListType = new TypeToken<ArrayList<Usuario>> () {}.getType();
        List<Usuario> users = null;
        try {
            users = gson.fromJson(
                    new FileReader(configuration.getPathUsuarios()),userListType
            );
            if (users == null)
                users = new ArrayList<>();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(),e);
            return Either.left(DataBaseError.ERROR_IN_FETCH);
        }
       return Either.right(users);
   }
   public Either<Error,Void> saveUsers (List<Usuario> users) {
       try (FileWriter fw = new FileWriter(configuration.getPathUsuarios())) {
           gson.toJson(users, fw);
       } catch (IOException e) {
           log.error(e.getMessage(),e);
           return Either.left(DataBaseError.ACTION_FAILED);
       }
       return Either.right(null);
   }


    public Either<Error,List<Mensaje>> loadMessages() {
        Type messageListType = new TypeToken<ArrayList<Mensaje>> () {}.getType();
        List<Mensaje> messages = null;
        try {
            messages = gson.fromJson(
                    new FileReader(configuration.getPathMensajes()), messageListType
            );
            if (messages == null)
                messages = new ArrayList<>();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(),e);
            return Either.left(DataBaseError.ERROR_IN_FETCH);
        }
        return Either.right(messages);
    }

    public Either<Error,Void> saveMessages(List<Mensaje> mensajes) {
        try (FileWriter fw = new FileWriter(configuration.getPathMensajes())) {
            gson.toJson(mensajes,fw);
            return Either.right(null);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return Either.left(DataBaseError.ACTION_FAILED);
        }
    }


    public Either<Error,List<Grupo>> loadGroups() {
        Type groupListType = new TypeToken<ArrayList<Grupo>> () {}.getType();
        List<Grupo> groups = null;
        try {
            groups = gson.fromJson(
                    new FileReader(configuration.getPathGrupos()), groupListType
            );
            if (groups == null)
                groups = new ArrayList<>();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(),e);
            return Either.left(DataBaseError.ERROR_IN_FETCH);
        }
        return Either.right(groups);
    }
    public Either<Error,Void> saveGroups(List<Grupo> groups) {
        try (FileWriter fw = new FileWriter(configuration.getPathGrupos())) {
            gson.toJson(groups,fw);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return Either.left(DataBaseError.ACTION_FAILED);
        }
        return Either.right(null);
    }
}
