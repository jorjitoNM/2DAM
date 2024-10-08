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

    private String pathUsuarios;
    private String pathGrupos;
    private String pathMensajes;
    private String pathMensajesGrupo;


    public ConfigurationFicheros() {
        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader()
                    .getResourceAsStream("config/config.properties"));
            this.pathUsuarios = p.getProperty("pathUsuarios");
            this.pathGrupos = p.getProperty("pathGrupos");
            this.pathMensajes = p.getProperty("pathMensajes");
            this.pathMensajesGrupo = p.getProperty("pathMensajesGrupo");

        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }
}
