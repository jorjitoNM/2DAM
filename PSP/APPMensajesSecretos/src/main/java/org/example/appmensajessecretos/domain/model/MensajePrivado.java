package org.example.appmensajessecretos.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MensajePrivado extends Mensaje{

    private final Map<String,String> keys; //<alias (nombre del usuario), contraseña>
    public MensajePrivado(String content, LocalDateTime date, String author, String grupo, Map<String,String> keys) {
        super(content, date, author, grupo);
        this.keys = keys;
    }

}
