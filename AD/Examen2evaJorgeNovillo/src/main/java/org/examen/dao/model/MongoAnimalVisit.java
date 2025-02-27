package org.examen.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MongoAnimalVisit {
    private Object _id;
    private LocalDate date;
    private List<MongoAnimal> animals;
    private MongoVisitor visitor;
}
