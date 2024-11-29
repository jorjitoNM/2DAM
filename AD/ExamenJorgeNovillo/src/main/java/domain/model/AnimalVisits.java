package domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement(name = "AnimalVisits")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnimalVisits {
    @XmlElement(name = "Visit")
    private List<AnimalVisit> animalVisits = new ArrayList<>();

    public AnimalVisits(List<AnimalVisit> animalVisits) {
        this.animalVisits = animalVisits;
    }
}
