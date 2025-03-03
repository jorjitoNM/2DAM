package org.example.backend.domain.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.common.Constantes;
import org.example.backend.dao.modelo.Cultivo;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TierraDTO {
    private String nombre;
    private String lat;
    private String lng;
    private double metrosCua;
    @JsonIgnoreProperties(Constantes.TIERRAS)
    private List<Cultivo> cultivos;
    private int numeroCultivos;
}
