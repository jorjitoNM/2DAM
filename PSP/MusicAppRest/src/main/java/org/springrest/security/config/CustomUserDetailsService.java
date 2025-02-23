package org.springrest.security.config;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springrest.domain.model.RolesEntity;
import org.springrest.domain.services.UserService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        org.springrest.domain.model.User user = service.findUserByEmail(email);
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(
                        user.getRoles().stream()
                                .map(RolesEntity::getRol)
                                .collect(Collectors.joining(",")))
                .authorities( user.getRoles().stream()
                        .map(RolesEntity::getRol)

                        .collect(Collectors.joining(",")))
                .build();
    }
}
