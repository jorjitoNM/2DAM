package org.examen.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "weapons")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@NamedQueries({
        @NamedQuery(name = "getAllWeapons", query = "from Weapon"),
        @NamedQuery(name = "getAllFactionWeapons", query = "from Weapon w join w.weaponsFactions wf where wf.faction.name = :faction_name")
})
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "wname")
    private String name;

    @Column(name = "wprice")
    private Double price;

    @OneToMany(mappedBy = "weapon", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<WeaponsFaction> weaponsFactions;

    public Weapon(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}