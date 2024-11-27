package domain.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "factions")
public class Factions {
    @XmlElement(name="faction")
    private List<Faction> factions;

    public Factions(List<Faction> factions) {
        this.factions = factions;
    }
}
