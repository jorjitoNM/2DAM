package org.example.appmensajessecretos.domain.modelo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Mensaje {
    private final String content;
    private final LocalDateTime date;
    private final Usuario author;
    private final Grupo group;

    @Override
    public String toString () {
        return content;
    }
}