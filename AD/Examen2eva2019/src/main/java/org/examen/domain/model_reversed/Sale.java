package org.examen.domain.model_reversed;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_weapons_faction")
    private WeaponsFaction idWeaponsFaction;

    @Column(name = "units")
    private Integer units;

    @Column(name = "sldate")
    private LocalDate date;

}