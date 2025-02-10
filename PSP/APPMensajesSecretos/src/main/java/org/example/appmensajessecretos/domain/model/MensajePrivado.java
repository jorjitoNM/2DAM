package org.example.appmensajessecretos.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class MensajePrivado extends Mensaje{

    private final Map<String,String> keys; //<alias (nombre del usuario), contraseña>

    public MensajePrivado(String content, LocalDateTime date, String author, String grupo, Map<String,String> keys) {
        super(content, date, author, grupo);
        this.keys = keys;
    }

}
