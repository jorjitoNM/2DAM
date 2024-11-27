package org.example.appmensajessecretos.domain.model;

import lombok.Data;
import org.example.appmensajessecretos.dao.model.UserRemote;
import org.example.appmensajessecretos.utilities.Constantes;
import org.springframework.security.core.userdetails.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
public class Usuario {
    private final String name;
    private final String password;
    private final Map<String,String> groupPasswords = new HashMap<>(); //<nombreGrupo, contraseña>

    public Usuario() {
        name = Constantes.CONTRASEÑA_INCORRECTA;
        password = "";
    }

    public Usuario (UserRemote user) {
        this.name = user.getName();
        this.password = Constantes.CONTRASEÑA_INCORRECTA;
        this.groupPasswords.putAll(user.getGroupPasswords());
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

    public void addGroupPassword (String groupName, String password) {
        groupPasswords.put(groupName,password);
    }

    public UserRemote toUserRemote() {
        return new UserRemote(this);
    }
}
