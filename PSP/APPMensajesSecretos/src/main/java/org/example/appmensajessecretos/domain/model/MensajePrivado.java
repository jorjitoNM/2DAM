package org.example.appmensajessecretos.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;

public class MensajePrivado extends Mensaje{

    private final HashMap<String,String> keys;
    public MensajePrivado(String content, LocalDateTime date, String author, String grupo) {
        super(content, date, author, grupo);
        this.keys = new HashMap<>();
    }

}
