package org.example.backend.domain.servicio;

import org.example.backend.dao.modelo.Cultivo;
import org.example.backend.dao.repositories.CultivosRep;
import org.springframework.stereotype.Service;

@Service
public class ServicioCultivos {

    private final CultivosRep cultivosRep;

    public ServicioCultivos(CultivosRep cultivosRep) {
        this.cultivosRep = cultivosRep;
    }


    public Cultivo addCultivo(Cultivo cultivo) {
        return cultivosRep.addCultivo(cultivo);
    }

    public void deleteCultivoSinTierra(Cultivo cultivo) {
        cultivosRep.deleteCultivoSinTierras(cultivo);
    }
}
