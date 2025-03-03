package org.springrest.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springrest.common.Constantes;
import org.springrest.domain.model.Cultivo;
import org.springrest.domain.model.Tierra;
import org.springrest.domain.services.TierrasService;
import org.springrest.ui.model.TierraDTO;

import java.util.List;

@RestController
@RequestMapping(Constantes.TIERRAS_URL)
@RequiredArgsConstructor
public class TierrasController {
    private final TierrasService tierrasService;

    @GetMapping(Constantes.GET_ALL)
    @PreAuthorize("hasRole(" + Constantes.ADMIN + ")")
    public List<Tierra> getAll() {
        return tierrasService.getAll();
    }


//    @GetMapping(Constantes.GET_ALL)
//    @PreAuthorize("hasRole(" + Constantes.USER + ")")
//    public List<TierraDTO> getAllMexicano() {
//        return tierrasService.getAllMexicano();
//    }


    @PostMapping(Constantes.CULTIVOS_URL)
    public List<Cultivo> getCultivos (@RequestBody Tierra tierra) {
        return tierrasService.getCultivos(tierra);
    }


    @GetMapping(Constantes.GET_ALL_SIN_CULTIVOS)
    @PreAuthorize("hasRole(" + Constantes.USER + ")")
    public List<Tierra> getAllSinCultivos () {
        return tierrasService.getAllSinCultivos();
    }

    @PutMapping(Constantes.ADD_URL)
    @PreAuthorize("hasRole(" + Constantes.ADMIN + ")")
    public boolean addTierra (@RequestBody Tierra tierra) {
        return tierrasService.addTierra(tierra);
    }
}
