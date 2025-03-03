package org.example.backend.ui.controllers;

import org.example.backend.common.Constantes;
import org.example.backend.components.MailComponent;
import org.example.backend.dao.modelo.User;
import org.example.backend.domain.servicio.ServicioUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constantes.API_REGISTRAR)
public class RegistroRestController {
    private final ServicioUser servicioUser;
    private final MailComponent mailComponent;

    public RegistroRestController(ServicioUser servicioUser, MailComponent mailComponent) {
        this.servicioUser = servicioUser;
        this.mailComponent = mailComponent;
    }

    @GetMapping
    public ResponseEntity<String> registrar(@RequestParam String username, @RequestParam String password) {
        User user2 = new User(username, password, false, null,null);
        if (servicioUser.addUsuario(user2)) {
            String code = servicioUser.getUserByName(user2).getCodigo();
            if (mailComponent.sendMail(Constantes.MAIL, Constantes.ACTIVAR_USUARIO,
                    Constantes.ACTIVAR_USER + code + Constantes.USUARIO_A_HTML)) {
                return ResponseEntity.ok(Constantes.ACTIVARLO);
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constantes.ERROR_AL_ENVIAR_EL_CORREO_PARA_USUARIO);
    }

    @GetMapping(Constantes.ACTIVAR)
    public void activar(@RequestParam String user) {
        servicioUser.activar(user);
    }
}
