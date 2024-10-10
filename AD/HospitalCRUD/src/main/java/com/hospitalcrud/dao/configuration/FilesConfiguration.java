package com.hospitalcrud.dao.configuration;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Log4j2
@Getter
public class FilesConfiguration {

    private static FilesConfiguration instance = null;
    private String pathPatients;
    private String pathDoctors;
    private String pathMedicalRecords;
    private String pathMedications;
    private int lastID;

    private FilesConfiguration() {
        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader()
                    .getResourceAsStream("config/config.properties"));
            this.pathPatients = p.getProperty("pathPatients");
            this.pathDoctors = p.getProperty("pathDoctors");
            this.pathMedicalRecords = p.getProperty("pathMedicalRecords");
            this.pathMedications = p.getProperty("pathMedications");
            this.lastID = Integer.parseInt(p.getProperty("nextID"));
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static FilesConfiguration getInstance() {
        if (instance==null) {
            instance=new FilesConfiguration();
        }
        return instance;
    }
    public void setID (int lastID) {
        this.lastID = lastID;
    }
}
