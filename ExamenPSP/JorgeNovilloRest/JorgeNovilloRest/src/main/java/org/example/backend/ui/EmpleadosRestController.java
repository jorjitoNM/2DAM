package org.example.backend.ui;

import lombok.RequiredArgsConstructor;
import org.example.backend.common.Constantes;
import org.example.backend.domain.model.User;
import org.example.backend.domain.service.EmpleadosService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constantes.EMPLEADOS)
@RequiredArgsConstructor
public class EmpleadosRestController {

    private final EmpleadosService empleadosService;

    @PutMapping(Constantes.ADD_USER_URL)
    @PreAuthorize("hasRole(" + Constantes.ADMIN_ROLE + ")")
    public ResponseEntity<User> addUser (@RequestBody User user) {
        return ResponseEntity.ok(empleadosService.addUser(user));
    }
}
