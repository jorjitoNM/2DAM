package org.examen.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Animals")
@NamedQueries(
        {
                @NamedQuery(name = "getAllAnimalsFromHabitat", query = "from Animal a where a.habitat.name = :habitat_name"),
                @NamedQuery(name = "getAnimalByName", query = "from Animal  where name = :animal_name")
        }
)
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Animal_ID")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Species")
    private String species;

    @Column(name = "Age")
    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Habitat_ID")
    private Habitat habitat;

    @OneToMany(mappedBy = "animal", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<AnimalVisit> animalVisits;

}