package org.example.appmensajessecretos.config;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Getter
@Component
@Log4j2
public class ConfigurationFicheros {

    private String pathJsonUsuarios;
    private String pathJsonGrupos;
    private String pathJsonMensajes;


    public ConfigurationFicheros() {
        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader()
                    .getResourceAsStream("config/config.properties"));
            this.pathJsonUsuarios = p.getProperty("pathUsuarios");
            this.pathJsonGrupos = p.getProperty("pathGrupos");
            this.pathJsonMensajes = p.getProperty("pathMensajes");

        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }
}
