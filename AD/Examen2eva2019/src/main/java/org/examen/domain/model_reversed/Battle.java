package org.examen.domain.model_reversed;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "battles")
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "bname")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faction_one")
    private Faction factionOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faction_two")
    private Faction factionTwo;

    @Column(name = "bplace")
    private String place;

    @Column(name = "bdate", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_spy", nullable = false)
    private Spy spy;

}