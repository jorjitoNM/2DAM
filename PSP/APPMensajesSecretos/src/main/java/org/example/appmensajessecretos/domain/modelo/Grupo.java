package org.example.appmensajessecretos.domain.modelo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Grupo {
    private final String name;
    private final String password;
    private final ArrayList<String> members;
    private final Boolean isPrivate;

    public Grupo(String name, String password, Boolean isPrivate) {
        this.name = name;
        this.password = password;
        this.isPrivate = isPrivate;
        members = new ArrayList<>();
    }

    @Override
    public String toString() {
        String result = name;
        if (Boolean.TRUE.equals(isPrivate))
            result += " (private)";
        return result;
    }
}
