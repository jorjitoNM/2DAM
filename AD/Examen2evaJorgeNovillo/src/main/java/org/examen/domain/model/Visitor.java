package org.examen.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Visitors")
@NamedQuery(name = "getVisitorByName", query = "from Visitor where name = :visitor_name")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Visitor_ID")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "Tickets")
    private Integer tickets;

    @OneToMany(mappedBy = "visitor")
    private List<AnimalVisit> animalVisits;

}