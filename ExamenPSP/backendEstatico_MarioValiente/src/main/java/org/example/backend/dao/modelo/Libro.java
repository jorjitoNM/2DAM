package org.example.backend.dao.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.common.Constantes;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    private int libroId;
    private String titulo;
    private int anioPublicacion;
    private String genero;
    private String editorial;
    @JsonIgnoreProperties(Constantes.LIBROS_JSON)
    private List<Autor> autores = new ArrayList<>();
}
