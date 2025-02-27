package org.examen.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Habitats")
@NamedQuery(name = "getHabitat", query = "from Habitat where name = :name")
public class Habitat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Habitat_ID")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Type")
    private String type;
}