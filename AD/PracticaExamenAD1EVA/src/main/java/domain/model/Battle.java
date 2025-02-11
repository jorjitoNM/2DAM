package domain.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Battle {
    private int id;
    private String nombre;
    private Faction faction_one;
    private Faction faction_two;
    private String place;
    private LocalDate date;
    private int spyId;

    public Battle(int id, String nombre, Faction faction_one, Faction faction_two, String place, LocalDate date, int spyId) {
        this.id = id;
        this.nombre = nombre;
        this.faction_one = faction_one;
        this.faction_two = faction_two;
        this.place = place;
        this.date = date;
        this.spyId = spyId;
    }
}
