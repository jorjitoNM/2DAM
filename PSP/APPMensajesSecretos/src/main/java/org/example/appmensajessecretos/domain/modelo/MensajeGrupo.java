package org.example.appmensajessecretos.domain.modelo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MensajeGrupo {
    private final String content;
    private final LocalDateTime date;
    private final Usuario author;
    private final Grupo grupo;
}
