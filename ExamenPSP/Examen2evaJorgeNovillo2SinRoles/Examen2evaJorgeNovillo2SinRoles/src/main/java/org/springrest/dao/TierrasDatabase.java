package org.springrest.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springrest.common.Constantes;
import org.springrest.domain.errors.NotFoundException;
import org.springrest.domain.model.Tierra;

import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TierrasDatabase {
    private List<Tierra> tierras = new ArrayList<>();

    public boolean addTierra(Tierra tierra) {
        return tierras.add(tierra);
    }

    public Tierra getTierra (Tierra tierra) {
        return tierras.stream().filter(t -> t.getLat().equals(tierra.getLat()) && t.getLng().equals(tierra.getLng())).findFirst().orElseThrow(() -> new NotFoundException(Constantes.TIERRA_NOT_FOUND));
    }
}
