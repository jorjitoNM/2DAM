package org.example.appmensajessecretos.domain.modelo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Objects;

@Data
public class Grupo {
    private final String name;
    private final String password;
    private final ArrayList<Usuario> members;
    private final ArrayList<Mensaje> mensajes;

    public Grupo(String name, String password) {
        this.name = name;
        this.password = password;
        members = new ArrayList<>();
        mensajes = new ArrayList<>();
    }

    public ArrayList<String> getMessages () {
        ArrayList<String> messages = new ArrayList<>();
        mensajes.forEach(m -> messages.add(m.toString()));
        return messages;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return Objects.equals(name, grupo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, members, mensajes);
    }
}
