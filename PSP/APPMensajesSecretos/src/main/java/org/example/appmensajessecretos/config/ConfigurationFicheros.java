package org.example.appmensajessecretos.config;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.example.appmensajessecretos.Configuration;
import org.example.appmensajessecretos.utilities.Constantes;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Getter
@Component
@Log4j2
public class ConfigurationFicheros {

    private final Configuration configuration;
    private String pathUsuarios;
    private String pathGrupos;
    private String pathMensajes;
    private String pathKeyStore;
    private String keyStorePassword;
    private String pathMensajesPrivados;


    public ConfigurationFicheros(Configuration configuration) {
        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader()
                    .getResourceAsStream(Constantes.RUTA_CONFIG_PROPERTIES));
            this.pathUsuarios = p.getProperty(Constantes.PATH_USUARIOS);
            this.pathGrupos = p.getProperty(Constantes.PATH_GRUPOS);
            this.pathMensajes = p.getProperty(Constantes.PATH_MENSAJES);
            this.pathKeyStore = p.getProperty(Constantes.KEY_STORE_PATH);
            this.keyStorePassword = p.getProperty(Constantes.KEY_STORE_PASSWORD);
            this.pathMensajesPrivados = p.getProperty(Constantes.PATH_MENSAJES_PRIVADOS);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        this.configuration = configuration;
    }
}
