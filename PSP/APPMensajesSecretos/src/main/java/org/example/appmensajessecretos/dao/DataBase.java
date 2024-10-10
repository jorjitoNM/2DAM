package org.example.appmensajessecretos.dao;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import org.example.appmensajessecretos.config.ConfigurationFicheros;
import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Mensaje;
import org.example.appmensajessecretos.domain.modelo.MensajeGrupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;
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

   public List<Usuario> loadUsers () {
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
        }
       return users;
   }
   public boolean saveUsers (List<Usuario> users) {
       try (FileWriter fw = new FileWriter(configuration.getPathUsuarios())) {
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
                    new FileReader(configuration.getPathMensajes()), messageListType
            );
            if (messages == null)
                messages = new ArrayList<>();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(),e);
        }
        return messages;
    }
    public boolean saveMessages(List<Mensaje> mensajes) {
       try (FileWriter fw = new FileWriter(configuration.getPathMensajes())) {
           gson.toJson(mensajes,fw);
       } catch (IOException e) {
           log.error(e.getMessage(),e);
           return true;
       }
       return true;
    }

    public List<MensajeGrupo> loadGroupMessages() {
        Type messageGrupoListType = new TypeToken<ArrayList<MensajeGrupo>> () {}.getType();
        List<MensajeGrupo> messages = null;
        try {
            messages = gson.fromJson(
                    new FileReader(configuration.getPathMensajesGrupo()), messageGrupoListType
            );
            if (messages == null)
                messages = new ArrayList<>();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(),e);
        }
        return messages;
    }
    public boolean saveGroupMessages(List<MensajeGrupo> mensajes) {
        try (FileWriter fw = new FileWriter(configuration.getPathMensajesGrupo())) {
            gson.toJson(mensajes,fw);
            return true;
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return false;
        }
    }

    public List<Grupo> loadGroups() {
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
        }
        return groups;
    }
    public boolean saveGroups(List<Grupo> groups) {
        try (FileWriter fw = new FileWriter(configuration.getPathGrupos())) {
            gson.toJson(groups,fw);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return true;
        }
        return true;
    }
}
