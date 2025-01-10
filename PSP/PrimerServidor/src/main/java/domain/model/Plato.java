package domain.model;

import java.util.List;

public record Plato(
    String nombre,
    List<String> ingredientes,
    int id
) {
}
