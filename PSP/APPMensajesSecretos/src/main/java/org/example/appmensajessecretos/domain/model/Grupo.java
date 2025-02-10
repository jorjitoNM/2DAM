package org.example.appmensajessecretos.domain.model;

import lombok.Data;
import org.example.appmensajessecretos.dao.model.GroupRemote;
import org.example.appmensajessecretos.dao.model.UserRemote;

import java.util.ArrayList;
import java.util.List;

@Data
public class Grupo {
    private final String name;
    private final String password;
    private final List<Usuario> members;
    private final Boolean isPrivate;

    public Grupo(String name, String password, Boolean isPrivate) {
        this.name = name;
        this.password = password;
        this.isPrivate = isPrivate;
        members = new ArrayList<>();
    }

    public Grupo (GroupRemote groupRemote) {
        this.name = groupRemote.getName();
        this.password = groupRemote.getPassword();
        this.members = groupRemote.getMembers().stream().map(UserRemote::toUser).toList();
        this.isPrivate = groupRemote.getIsPrivate();
    }

    @Override
    public String toString() {
        String result = name;
        if (Boolean.TRUE.equals(isPrivate))
            result += " (private)";
        return result;
    }
}
