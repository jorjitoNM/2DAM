package org.example.backend.domain.servicio;

import org.example.backend.dao.modelo.User;
import org.example.backend.dao.repositories.UserRep;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class ServicioUser {
    private final UserRep usuarios;
    private final PasswordEncoder passwordEncoder;

    public ServicioUser(UserRep usuarios, PasswordEncoder passwordEncoder) {
        this.usuarios = usuarios;
        this.passwordEncoder = passwordEncoder;
    }

    private String codigoAleatorio() {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        Base64.getEncoder().encodeToString(salt);
        return Base64.getUrlEncoder().encodeToString(salt);
    }

    public boolean addUsuario(User usuario) {
        User user = usuarios.findAll()
                .stream()
                .filter(user1 -> usuario.getNombre().equals(user1.getNombre())
                        && passwordEncoder.matches(usuario.getPassword(), user1.getPassword()))
                .findFirst().orElse(null);
        if (user == null) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuario.setCodigo(codigoAleatorio());
            usuarios.save(usuario);
            return true;
        }
        return false;
    }

    public User getUserByName(User user2) {
        return usuarios.findByName(user2.getNombre());
    }

    public void activar(String codigo) {
        usuarios.findAll().stream()
                .filter(user1 -> user1.getCodigo().equals(codigo)).findFirst()
                .ifPresent(user -> user.setActivado(true));
    }
}
