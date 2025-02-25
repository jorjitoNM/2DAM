package org.examen.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "faction")
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "GET_FACTION", query = "from Faction where name = :name")
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

    @OneToMany(mappedBy = "faction", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<WeaponsFaction> weaponsFactions;

    public Faction(String name, String contact, String planet, Integer controlledSystems, LocalDate lastPurchase) {
        this.name = name;
        this.contact = contact;
        this.planet = planet;
        this.controlledSystems = controlledSystems;
        this.lastPurchase = lastPurchase;
    }
}