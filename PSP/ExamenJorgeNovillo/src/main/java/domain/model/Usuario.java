package domain.model;

import lombok.Data;

@Data
public class Usuario {
    private final String name;
    private final String password;
    private final String cuenta;

    public Usuario() {
        this.name = "juan";
        this.password = "1234";
        this.cuenta = "cuentaDeJuan";
    }

    public Usuario(String password, String name) {
        this.password = password;
        this.name = name;
        this.cuenta = "loTieneElCasinoYa";
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "name='" + name + '\'' +
                ", cuenta='" + cuenta + '\'' +
                '}';
    }
}
