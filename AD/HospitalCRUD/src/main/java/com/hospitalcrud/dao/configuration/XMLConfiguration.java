package com.hospitalcrud.dao.configuration;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Log4j2
@Getter
public class XMLConfiguration {
    private static XMLConfiguration instance = null;
    private Path pathMedicalRecords;

    private XMLConfiguration() {
        try {
            Properties p = new Properties();
            p.loadFromXML(getClass().getClassLoader()
                    .getResourceAsStream("config/properties.xml"));
            this.pathMedicalRecords = Paths.get("pathMedicalRecords");
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static XMLConfiguration getInstance() {
        if (instance==null) {
            instance=new XMLConfiguration();
        }
        return instance;
    }
}
