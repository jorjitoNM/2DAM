package com.hospitalcrud.dao.configuration;

import lombok.Getter;

import java.io.IOException;
import java.util.Properties;

@Getter
public class FilesConfiguration {

    private static FilesConfiguration instance = null;
    private String pathPatients;
    private String pathDoctors;
    private String pathMedicalRecords;
    private String pathMedications;

    private FilesConfiguration() {
        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader()
                    .getResourceAsStream("config/config.properties"));
            this.pathPatients = p.getProperty("pathPatients");
            this.pathDoctors = p.getProperty("pathDoctors");
            this.pathMedicalRecords = p.getProperty("pathMedicalRecords");
            this.pathMedications = p.getProperty("pathMedications");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FilesConfiguration getInstance() {
        if (instance==null) {
            instance=new FilesConfiguration();
        }
        return instance;
    }
}
