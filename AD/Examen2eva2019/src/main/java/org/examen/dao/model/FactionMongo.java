package org.examen.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.examen.domain.model.Weapon;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FactionMongo {
    private String name;
    private String contact;
    private String planet;
    private int controlledSystems;
    private LocalDate lastPurchase;
    private List<Weapon> weapons;
}
