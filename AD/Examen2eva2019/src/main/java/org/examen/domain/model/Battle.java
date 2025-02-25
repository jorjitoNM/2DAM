package org.examen.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "battles")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Battle(String name, Faction factionOne, Faction factionTwo, String place, LocalDate date, Spy spy) {
        this.name = name;
        this.factionOne = factionOne;
        this.factionTwo = factionTwo;
        this.place = place;
        this.date = date;
        this.spy = spy;
    }
}