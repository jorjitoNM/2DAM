package org.springrest.security.config;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springrest.common.Constantes;
import org.springrest.dao.UsersDatabase;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsersDatabase userRepository;

    public CustomUserDetailsService(UsersDatabase userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = Optional.ofNullable(userRepository.findUserByEmail(username)).orElseThrow(() ->
                new UsernameNotFoundException(Constantes.USER_NOT_FOUND));
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRol())
                .build();
    }
}
