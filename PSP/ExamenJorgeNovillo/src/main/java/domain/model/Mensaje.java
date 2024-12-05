package domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mensaje {
    private final String contenido;
    private final String groupName;
    private final String sign;

    public Mensaje(String contenido, String groupName) {
        this.contenido = contenido;
        this.groupName = groupName;
        this.sign = null;
    }
}
