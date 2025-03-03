package org.springrest.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cultivo {
    private String nombre;
    private int valorEnergetico;
    private String caracteristicas;
    private List<Tierra> tierras;
}
