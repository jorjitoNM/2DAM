package org.example.backend.dao.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.common.Constantes;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Autor {

    private int autorId;
    private String nombre;
    private String fechaNac;
    private String fechaMuerte;
    private String lugarNac;
    @JsonIgnoreProperties(Constantes.AUTORES)
    private List<Libro> libros = new ArrayList<>();
}
