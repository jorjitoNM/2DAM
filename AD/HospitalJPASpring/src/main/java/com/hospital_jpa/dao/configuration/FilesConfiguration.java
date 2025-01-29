package com.hospital_jpa.dao.configuration;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Log4j2
@Component
@Getter
public class FilesConfiguration {

    private String pathLoggedUser;

    private FilesConfiguration() {
        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader()
                    .getResourceAsStream("config/properties"));
            this.pathLoggedUser = p.getProperty("pathLoggedUser");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
