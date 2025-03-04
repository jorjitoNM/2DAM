package org.example.backend.domain.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.dao.repositories.UserDatabase;
import org.example.backend.domain.model.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpleadosService {
    private final UserDatabase database;

    public User addUser(User user) {
        return database.addUser(user);
    }
}
