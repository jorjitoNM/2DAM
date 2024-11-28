package domain.model;

import data.mappers.LocalDateAdapter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement(name = "faction")
@XmlAccessorType(XmlAccessType.NONE)
public class Faction {
    @XmlElement
    private String name;
    @XmlElement
    private String contact;
    @XmlElement
    private String planet;
    @XmlElement
    private int numberCS;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate dateLastPurchase;
    @XmlElementWrapper(name="weapons")
    @XmlElement(name = "weapon")
    private List<Weapon> weapons = new ArrayList<>();

    public Faction(String name, String contact, String planet, int numberCS, LocalDate dateLastPurchase, List<Weapon> weapons) {
        this.name = name;
        this.contact = contact;
        this.planet = planet;
        this.numberCS = numberCS;
        this.dateLastPurchase = dateLastPurchase;
        this.weapons = weapons;
    }
}
