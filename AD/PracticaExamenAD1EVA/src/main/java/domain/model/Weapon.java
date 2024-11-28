package domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "weapon")
@XmlAccessorType(XmlAccessType.NONE)
public class Weapon {
    @XmlTransient
    private int id;
    @XmlElement
    private String name;
    @XmlElement
    private int price;

    public Weapon(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public Weapon() {
        this.id = 1;
        this.name = "Knife";
        this.price = 25;
    }
}
