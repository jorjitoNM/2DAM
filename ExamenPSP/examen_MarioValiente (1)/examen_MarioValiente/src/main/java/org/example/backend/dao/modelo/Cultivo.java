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
public class Cultivo {
    private String nombre;
    private double valorEnergetico;
    private String caracteristcas;
    @JsonIgnoreProperties(Constantes.CULTIVOS)
    private List<Tierra> tierras;

    public Cultivo(String nombre) {
        this.nombre = nombre;
    }
}
