package domain.model;

import data.mappers.LocalDateAdapter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@XmlRootElement(name = "Visit")
@XmlAccessorType(XmlAccessType.NONE)
public class AnimalVisit {
    @XmlElement(name = "AnimalID")
    private int animalId;
    @XmlElement(name = "VisitorID")
    private int visitorId;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate VisitDate;

    public AnimalVisit(int animalId, int visitorId, LocalDate visitDate) {
        this.animalId = animalId;
        this.visitorId = visitorId;
        VisitDate = visitDate;
    }
}
