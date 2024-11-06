package org.example.appmensajessecretos.domain.model;

import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class Mensaje {
    private final String content;
    private final LocalDateTime date;
    private final String author;
    private final String grupo;

    @Override
    public String toString() {
        return "De " + author + ": " + content
                + " (" + date.getDayOfMonth() + "/" + date.getMonth() + "/" + date.getYear() + ")";
    }
}
