package org.example.appmensajessecretos.domain.model;

import lombok.Data;
import org.example.appmensajessecretos.utilities.Constantes;

import java.util.Objects;

@Data
public class Usuario {
    private final String name;
    private final String password;

    public Usuario() {
        name = Constantes.CONTRASEÑA_INCORRECTA;
        password = "";
    }

    public Usuario(String name, String password) {
        this.name = name;
        this.password = password;
    }

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

    @Override
    public String toString() {
        return name;
    }
}
