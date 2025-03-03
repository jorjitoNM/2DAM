package org.example.backend.domain.servicio;

import org.example.backend.common.Constantes;
import org.example.backend.dao.modelo.Cultivo;
import org.example.backend.dao.modelo.Tierra;
import org.example.backend.dao.repositories.TierrasRep;
import org.example.backend.domain.errors.RecursoNotFound;
import org.example.backend.domain.modelo.TierraDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioTierras {
    private final TierrasRep tierrasRep;

    public ServicioTierras(TierrasRep tierrasRep) {
        this.tierrasRep = tierrasRep;
    }

    public Tierra addTierra(Tierra tierra) {
        return tierrasRep.addTierra(tierra);
    }

    public List<TierraDTO> getTierras() {
        return tierrasRep.getTierrasDTO(tierrasRep.getTierras());
    }

    public List<Cultivo> getCultivoDeTierra(Tierra tierra) {
        Tierra tierra1 = Optional.ofNullable(tierrasRep.getTierraByNombre(tierra))
                .orElseThrow(() -> new RecursoNotFound(Constantes.NO_SE_HA_ENCONTRADO_LA_TIERRA));
        return tierrasRep.getCultiosDeTierra(tierra1);
    }

    public List<Tierra> getTierrasSinCultivos() {
        return tierrasRep.getTierraSinCultivos();
    }

    public List<Tierra> getTierraConCultivo(Cultivo cultivo) {
        return tierrasRep.getTierraConCultivo(cultivo);
    }
}
