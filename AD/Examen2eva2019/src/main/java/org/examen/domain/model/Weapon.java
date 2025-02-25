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
@Table(name = "weapons")
@AllArgsConstructor
@NoArgsConstructor
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