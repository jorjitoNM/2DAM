package domain.model;

import lombok.Data;

@Data
public class Apuesta {
    private final int numero;
    private final String usuario;
    private final int cantidad;


    @Override
    public String toString() {
        return "Apuesta{" +
                "numero=" + numero +
                ", usuario='" + usuario + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
