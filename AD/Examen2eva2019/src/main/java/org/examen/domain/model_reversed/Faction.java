package org.examen.domain.model_reversed;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "faction")
public class Faction {
    @Id
    @Column(name = "fname")
    private String name;

    @Column(name = "contact")
    private String contact;

    @Column(name = "planet")
    private String planet;

    @Column(name = "number_controlled_systems")
    private Integer controlledSystems;

    @Column(name = "date_last_purchase")
    private LocalDate lastPurchase;

    @OneToMany(mappedBy = "factionOne")
    private Set<Battle> battlesOne = new LinkedHashSet<>();

    @OneToMany(mappedBy = "factionTwo")
    private Set<Battle> battlesTwo = new LinkedHashSet<>();

    @OneToMany(mappedBy = "nameFaction")
    private Set<WeaponsFaction> weaponsFactions = new LinkedHashSet<>();

}