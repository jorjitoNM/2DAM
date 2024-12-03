package org.example.appmensajessecretos.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(force = true)
public class Mensaje {
    private final String content;
    private final LocalDateTime date;
    private final String author;
    private final String grupo;
    private final String sign;

    public Mensaje(String content, String author, String grupo) {
        this.content = content;
        this.author = author;
        this.grupo = grupo;
        date = LocalDateTime.now();
        this.sign = null;
    }

    public Mensaje(String content, LocalDateTime date, String author, String grupo) {
        this.content = content;
        this.date = date;
        this.author = author;
        this.grupo = grupo;
        this.sign = null;
    }

    public Mensaje(String content, LocalDateTime date, String author, String grupo, String sign) {
        this.content = content;
        this.date = date;
        this.author = author;
        this.grupo = grupo;
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "De " + author + ": " + content
                + " (" + date.getDayOfMonth() + "/" + date.getMonth() + "/" + date.getYear() + ")";
    }
}
