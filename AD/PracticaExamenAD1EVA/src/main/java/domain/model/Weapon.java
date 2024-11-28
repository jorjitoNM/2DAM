package domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@XmlRootElement(name = "weapon")
@XmlAccessorType(XmlAccessType.NONE)
public class Weapon {
    @XmlElement
    private String name;
    @XmlElement
    private int price;
}
