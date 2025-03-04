package org.example.backend.domain.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.dao.repositories.UserDatabase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDatabase database;

    public boolean checkPassword(String username,String password) {
        return database.checkPassword(username,password);
    }
}
