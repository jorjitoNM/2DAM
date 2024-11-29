package domain.model;

import lombok.Data;

@Data
public class Visitor {
    private int id;
    private String nombre;
    private String email;
    private int tickets;

    public Visitor(String nombre, String email, int tickets) {
        this.nombre = nombre;
        this.email = email;
        this.tickets = tickets;
    }
}
