package org.example.backend.dao.repositories;

import org.example.backend.dao.modelo.Cultivo;
import org.example.backend.dao.modelo.Tierra;
import org.springframework.stereotype.Repository;

@Repository
public class CultivosTierrasRep {
    public CultivosTierrasRep(TierrasRep tierrasRep, CultivosRep cultivosRep) {
        Tierra tierra = tierrasRep.getTierras().getFirst();
        Cultivo cultivo = cultivosRep.getCultivos().getFirst();
        asociarCultivoaTierra(cultivo, tierra);

        Tierra tierra2 = tierrasRep.getTierras().get(1);
        Cultivo cultivo2 = cultivosRep.getCultivos().get(1);
        asociarCultivoaTierra(cultivo2, tierra2);


    }

    public void asociarCultivoaTierra(Cultivo cultivo, Tierra tierra) {
        cultivo.getTierras().add(tierra);
        tierra.getCultivos().add(cultivo);
    }
}
