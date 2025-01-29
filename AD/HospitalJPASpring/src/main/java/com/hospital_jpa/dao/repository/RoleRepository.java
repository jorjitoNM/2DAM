package com.hospital_jpa.dao.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hospital_jpa.dao.configuration.FilesConfiguration;
import com.hospital_jpa.dao.model.FileUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

@Repository
@Log4j2
public class RoleRepository {

    private final FilesConfiguration filesConfiguration;
    private final Gson gson;

    public RoleRepository(FilesConfiguration filesConfiguration, Gson gson) {
        this.filesConfiguration = filesConfiguration;
        this.gson = gson;
    }

    public void saveFileUser(FileUser fileUser) {
        try (FileWriter fileWriter = new FileWriter(filesConfiguration.getPathLoggedUser())) {
            gson.toJson(fileUser,fileWriter);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }

    public FileUser loadFileUser() {
        Type fileUserType = new TypeToken<FileUser>() {}.getType();
        try (FileReader fileReader = new FileReader(filesConfiguration.getPathLoggedUser())) {
            return gson.fromJson(fileReader,fileUserType);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }
}
