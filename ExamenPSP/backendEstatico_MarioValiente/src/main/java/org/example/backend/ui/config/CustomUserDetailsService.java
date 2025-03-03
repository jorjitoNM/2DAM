package org.example.backend.ui.config;


import org.example.backend.common.Constantes;
import org.example.backend.dao.repositories.UserRep;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRep userRepository;

    public CustomUserDetailsService(UserRep userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = Optional.ofNullable(userRepository.findByName(username)).orElseThrow(() ->
                new UsernameNotFoundException(Constantes.USER_NOT_FOUND));
        return User.builder()
                .username(user.getNombre())
                .password(user.getPassword())
                .roles(user.getRol())
                .build();
    }
}
