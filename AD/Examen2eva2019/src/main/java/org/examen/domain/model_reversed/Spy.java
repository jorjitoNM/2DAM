package org.examen.domain.model_reversed;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "spies")
public class Spy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "sname")
    private String name;

    @Column(name = "srace")
    private String race;

    @OneToMany(mappedBy = "idSpy")
    private List<Battle> battles;

}