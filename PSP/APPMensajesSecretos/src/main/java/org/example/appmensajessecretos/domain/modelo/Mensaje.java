package org.example.appmensajessecretos.domain.modelo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class Mensaje {
    private final String content;
    private final LocalDateTime date;
    private final Usuario author;
    private final ArrayList<Usuario> receivers;

    @Override
    public String toString() {
        return "De " + author.getName() + " para " + parseReceivers() + ": " + content
                + " (" + date.getDayOfMonth() + "/" + date.getMonth() + "/" + date.getYear() + ")";
    }

    private String parseReceivers() {
        StringBuilder sb = new StringBuilder();
        receivers.forEach(r -> sb.append(r.getName()).append(", "));
        return sb.toString();
    }
}