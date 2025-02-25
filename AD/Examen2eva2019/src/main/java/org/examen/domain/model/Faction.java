package org.examen.domain.model;

import java.time.LocalDate;
import java.util.List;


public class Faction {
    private String name;
    private String contact;
    private String planet;
    private int numberCS;
    private LocalDate dateLastPurchase;
    private List<Weapon> weapons;
}
