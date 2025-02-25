package org.examen.domain.model_reversed;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "weapons_factions")
public class WeaponsFaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name_faction")
    private Faction nameFaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_weapon")
    private Weapon idWeapon;

    @OneToMany(mappedBy = "idWeaponsFaction")
    private Set<Sale> sales = new LinkedHashSet<>();

}