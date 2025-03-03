package org.example.backend.dao.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.common.Constantes;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tierra {
    private String nombre;
    private String lat;
    private String lng;
    private double metrosCua;
    @JsonIgnoreProperties(Constantes.TIERRAS)
    private List<Cultivo> cultivos;

    public Tierra(String nombre) {
        this.nombre = nombre;
    }
}
