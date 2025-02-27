package org.examen.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "weapons_factions")
@AllArgsConstructor
@NoArgsConstructor
public class WeaponsFaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "name_faction")
    private Faction faction;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_weapon")
    private Weapon weapon;

    @OneToMany(mappedBy = "idWeaponsFaction")
    private List<Sale> sales;

    public WeaponsFaction(Faction faction, Weapon weapon) {
        this.faction = faction;
        this.weapon = weapon;
    }

    public WeaponsFaction(Faction faction) {
        this.faction = faction;
    }
}