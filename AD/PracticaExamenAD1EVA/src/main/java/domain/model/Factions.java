package domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement(name = "factions")
@XmlAccessorType(XmlAccessType.FIELD)
public class Factions {
    @XmlElement(name="faction")
    private List<Faction> factions;

    public Factions(List<Faction> factions) {
        this.factions = factions;
    }
}
