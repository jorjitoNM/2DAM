package org.example.appmensajessecretos.domain.modelo;

import lombok.Data;

import java.util.Objects;

@Data
public class Usuario {
    private final String name;
    private final String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(name, usuario.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
