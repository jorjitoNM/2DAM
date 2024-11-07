package org.example.appmensajessecretos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class Mensaje {
    private final String content;
    private final LocalDateTime date;
    private final String author;
    private final String grupo;

    public Mensaje(String content, String author, String grupo) {
        this.content = content;
        this.author = author;
        this.grupo = grupo;
        date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "De " + author + ": " + content
                + " (" + date.getDayOfMonth() + "/" + date.getMonth() + "/" + date.getYear() + ")";
    }
}
