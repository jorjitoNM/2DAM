package org.springrest.domain.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springrest.dao.TierrasDatabase;
import org.springrest.domain.model.Cultivo;
import org.springrest.domain.model.Tierra;
import org.springrest.ui.model.TierraDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TierrasService {

    private final TierrasDatabase tierrasDatabase;


    public List<Tierra> getAll() {
        return tierrasDatabase.getTierras();
    }

    public List<TierraDTO> getAllMexicano() {
        return tierrasDatabase.getTierras().stream().map(Tierra::toTierraDTO).toList();
    }

    public boolean addTierra (Tierra tierra) {
        return tierrasDatabase.addTierra(tierra);
    }


    public List<Cultivo> getCultivos (Tierra tierra) {
        return tierrasDatabase.getTierra(tierra).getCultivos();
    }

    public List<Tierra> getAllSinCultivos() {
        return tierrasDatabase.getTierras().stream().filter(t -> t.getCultivos().isEmpty()).toList();
    }
}
