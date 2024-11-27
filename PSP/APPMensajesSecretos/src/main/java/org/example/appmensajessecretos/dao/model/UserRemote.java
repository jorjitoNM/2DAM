package org.example.appmensajessecretos.dao.model;

import lombok.Data;
import org.example.appmensajessecretos.domain.model.Usuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
public class UserRemote {
    private final String name;
    private final Map<String,String> groupPasswords = new HashMap<>(); //<nombreGrupo, contraseña>

    public UserRemote(Usuario user) {
        name = user.getName();
        groupPasswords.putAll(user.getGroupPasswords());
    }

    public UserRemote(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRemote usuario = (UserRemote) o;
        return Objects.equals(name, usuario.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

    public void addGroupPassword (String groupName, String password) {
        groupPasswords.put(groupName,password);
    }

    public Usuario toUser() {
        return new Usuario(this);
    }
}
