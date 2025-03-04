package org.example.backend.security.config;


import org.example.backend.common.Constantes;
import org.example.backend.dao.repositories.UserDatabase;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDatabase userRepository;

    public CustomUserDetailsService(UserDatabase userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = Optional.ofNullable(userRepository.findByName(username)).orElseThrow(() ->
                new UsernameNotFoundException(Constantes.USER_NOT_FOUND));
        return User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getRol())
                .build();
    }
}
