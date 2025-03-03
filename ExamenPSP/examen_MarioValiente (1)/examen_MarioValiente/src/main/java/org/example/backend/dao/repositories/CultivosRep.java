package org.example.backend.dao.repositories;

import org.example.backend.dao.modelo.Cultivo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CultivosRep {

    private final List<Cultivo> cultivos = new ArrayList<>();

    public CultivosRep() {
        cultivos.add(new Cultivo("cultivo1", 230, "Cultivo grande", new ArrayList<>()));
        cultivos.add(new Cultivo("cultivo2", 456, "Cultivo pequeño", new ArrayList<>()));
        cultivos.add(new Cultivo("cultivo3", 434, "Cultivo pequeño", new ArrayList<>()));
    }

    public List<Cultivo> getCultivos() {
        return cultivos;
    }

    public Cultivo addCultivo(Cultivo cultivo) {
        cultivos.add(cultivo);
        return cultivo;
    }

    public void deleteCultivoSinTierras(Cultivo cultivo) {
        cultivos.remove(cultivo);
    }
}
