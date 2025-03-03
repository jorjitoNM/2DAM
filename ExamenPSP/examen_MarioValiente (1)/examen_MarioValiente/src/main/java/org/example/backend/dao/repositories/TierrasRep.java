package org.example.backend.dao.repositories;

import org.example.backend.dao.modelo.Cultivo;
import org.example.backend.dao.modelo.Tierra;
import org.example.backend.domain.modelo.TierraDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TierrasRep {
    private final List<Tierra> tierras = new ArrayList<>();

    public TierrasRep() {
        tierras.add(new Tierra("Tierra1", "32424", "42424", 23.43, new ArrayList<>()));
        tierras.add(new Tierra("Tierra2", "4242", "125623", 22.43, new ArrayList<>()));
        tierras.add(new Tierra("Tierra3", "4242", "125623", 22.43, new ArrayList<>()));
    }

    public Tierra addTierra(Tierra tierra) {
        tierras.add(tierra);
        return tierra;
    }

    public List<Tierra> getTierras() {
        return tierras;
    }

    public List<TierraDTO> getTierrasDTO(List<Tierra> tierras) {
        List<TierraDTO> tierraDTOs = new ArrayList<>();
        for (Tierra tierra : tierras) {
            tierraDTOs.add(new TierraDTO(
                    tierra.getNombre(),
                    tierra.getLat(),
                    tierra.getLng(),
                    tierra.getMetrosCua(),
                    tierra.getCultivos(),
                    tierra.getCultivos().size()));
        }
        return tierraDTOs;
    }

    public Tierra getTierraByNombre(Tierra tierra2) {
        return tierras.stream().filter(tierra -> tierra.getNombre().equals(tierra2.getNombre())).findFirst().orElse(null);
    }

    public List<Cultivo> getCultiosDeTierra(Tierra tierra1) {
        return tierra1.getCultivos();
    }

    public List<Tierra> getTierraSinCultivos() {
        List<Tierra> tierraSinCultivos = new ArrayList<>();
        tierras.forEach(tierra -> {
            if (tierra.getCultivos().isEmpty()) {
                tierraSinCultivos.add(tierra);
            }
        });
        return tierraSinCultivos;
    }

    public List<Tierra> getTierraConCultivo(Cultivo cultivo) {
        List<Tierra> tierraConCultivo = new ArrayList<>();
        tierras.forEach(tierra -> {
            for (Cultivo cultivo1 : tierra.getCultivos()) {
                if (cultivo1.getNombre().equals(cultivo.getNombre())) {
                    tierraConCultivo.add(tierra);
                }
            }
        });
        return tierraConCultivo;
    }
}
