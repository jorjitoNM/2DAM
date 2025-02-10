package org.example.appmensajessecretos.dao.model;

import lombok.Data;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Usuario;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupRemote {
    private final String name;
    private final String password;
    private final List<UserRemote> members;
    private final Boolean isPrivate;

    public GroupRemote(String name, String password, Boolean isPrivate) {
        this.name = name;
        this.password = password;
        this.isPrivate = isPrivate;
        members = new ArrayList<>();
    }
    public GroupRemote (Grupo grupo) {
        this.name = grupo.getName();
        this.password = grupo.getPassword();
        this.isPrivate = grupo.getIsPrivate();
        members = grupo.getMembers().stream().map(Usuario::toUserRemote).toList();
    }

    public Grupo toGroup() {
        return new Grupo(this);
    }
}
