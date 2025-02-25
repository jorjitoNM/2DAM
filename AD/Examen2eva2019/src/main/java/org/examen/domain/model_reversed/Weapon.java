package org.examen.domain.model_reversed;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "weapons")
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "wname")
    private String name;

    @Column(name = "wprice")
    private Double price;

    @OneToMany(mappedBy = "idWeapon")
    private Set<WeaponsFaction> weaponsFactions = new LinkedHashSet<>();

}