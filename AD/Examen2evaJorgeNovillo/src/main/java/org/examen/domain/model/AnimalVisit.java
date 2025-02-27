package org.examen.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Animal_Visits")
@AllArgsConstructor
@NoArgsConstructor
public class AnimalVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Animal_ID")
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Visitor_ID")
    private Visitor visitor;

    @Column(name = "Visit_Date")
    private LocalDate visitDate;

    public AnimalVisit(Animal animal, Visitor visitor, LocalDate visitDate) {
        this.animal = animal;
        this.visitor = visitor;
        this.visitDate = visitDate;
    }
}