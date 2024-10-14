package org.example.appmensajessecretos.domain.modelo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Grupo {
    private final String name;
    private final String password;
    private final ArrayList<Usuario> members;

    public Grupo(String name, String password) {
        this.name = name;
        this.password = password;
        members = new ArrayList<>();
    }

    @Override
    public String toString() {
        return name;
    }
}
