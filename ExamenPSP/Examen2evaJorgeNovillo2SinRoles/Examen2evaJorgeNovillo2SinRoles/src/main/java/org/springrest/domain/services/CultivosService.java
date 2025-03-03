package org.springrest.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springrest.dao.CultivosDatabase;
import org.springrest.domain.model.Cultivo;
import org.springrest.domain.model.Tierra;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CultivosService {
    private final CultivosDatabase cultivosDatabase;

    public void addCultivo(Cultivo cultivo) {
        cultivosDatabase.addCultivo(cultivo);
    }

    public boolean deleteCultivoColombiano(Cultivo cultivo) {
        return cultivosDatabase.delete(cultivo);
    }

    public List<Tierra> get(Cultivo cultivo) {
        return cultivosDatabase.getCultivo(cultivo).getTierras();
    }
}
