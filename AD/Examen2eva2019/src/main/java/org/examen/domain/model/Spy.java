package org.examen.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "spies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "sname")
    private String name;

    @Column(name = "srace")
    private String race;

    @OneToMany(mappedBy = "spy",cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Battle> battles;

    public Spy(String name, String race, List<Battle> battles) {
        this.name = name;
        this.race = race;
        this.battles = battles;
    }
}