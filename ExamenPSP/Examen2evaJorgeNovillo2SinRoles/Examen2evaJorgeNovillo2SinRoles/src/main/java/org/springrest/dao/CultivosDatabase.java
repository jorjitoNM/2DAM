package org.springrest.dao;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.springrest.common.Constantes;
import org.springrest.domain.errors.NotFoundException;
import org.springrest.domain.model.Cultivo;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class CultivosDatabase {
    private List<Cultivo> cultivos = new ArrayList<>();

    public void addCultivo(Cultivo cultivo) {
        cultivos.add(cultivo);
    }

    public boolean delete(Cultivo cultivo) {
        if (cultivos.stream().anyMatch(c -> c.getNombre().equals(cultivo.getNombre()))) {
            return cultivos.remove(cultivo);
        }
        else
            throw new NotFoundException(Constantes.CULTIVO_NOT_FOUND);
    }

    public Cultivo getCultivo(Cultivo cultivo) {
        return cultivos.stream().filter(c -> c.getNombre().equals(cultivo.getNombre())).findFirst().orElseThrow(() -> new NotFoundException(Constantes.CULTIVO_NOT_FOUND));
    }
}
