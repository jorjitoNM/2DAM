package org.example.backend.domain.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.common.Constantes;
import org.example.backend.dao.repositories.CosasDatabase;
import org.example.backend.dao.repositories.UserDatabase;
import org.example.backend.domain.model.Cosa;
import org.example.backend.domain.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CosasService {

    private final CosasDatabase database;
    private final UserDatabase userDatabase;

    public List<Cosa> getAllInfo() {
        return database.getAllInfo();
    }

    public List<Cosa> getAll(String name) {
        return database.getAll(name);
    }

    public Cosa update(Cosa cosa, String name) {
        User user = userDatabase.findByName(name);
        if (user == null)
            throw new UsernameNotFoundException(Constantes.USER_NOT_FOUND);
        return database.update(cosa,user);
    }

    public void delete(int id) {
        database.delete(id);
    }
}
