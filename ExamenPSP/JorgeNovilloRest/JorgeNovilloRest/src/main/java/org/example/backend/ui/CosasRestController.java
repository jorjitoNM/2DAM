package org.example.backend.ui;

import lombok.RequiredArgsConstructor;
import org.example.backend.common.Constantes;
import org.example.backend.domain.model.Cosa;
import org.example.backend.domain.service.CosasService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constantes.COSAS_URL)
@RequiredArgsConstructor
public class CosasRestController {

    private final CosasService cosasService;

    @GetMapping(Constantes.GET_ALL)
    @PreAuthorize("hasRole("+ Constantes.ADMIN_ROLE +")")
    public ResponseEntity<List<Cosa>> getAllInfo () {
        return ResponseEntity.ok(cosasService.getAllInfo());
    }

    @GetMapping(Constantes.GET_COSAS)
    @PreAuthorize("hasRole(" + Constantes.USER_ROLE + ")")
    public List<Cosa> getMisCosas () {
        return cosasService.getAll(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PostMapping(Constantes.UPDATE)
    @PreAuthorize("hasRole(" + Constantes.USER_ROLE + ")")
    public ResponseEntity<Cosa> updateCosa (@RequestBody Cosa cosa) {
        return ResponseEntity.ok(cosasService.update(cosa,SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    @GetMapping(Constantes.DELETE + "/{" + Constantes.ID + "}")
    @PreAuthorize("hasRole(" + Constantes.USER_ROLE + ")")
    public ResponseEntity<Void> deleteCosa (@PathVariable int id) {
        cosasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
