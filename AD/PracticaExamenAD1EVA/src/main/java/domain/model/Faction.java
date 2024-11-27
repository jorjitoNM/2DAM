package domain.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Faction {
    private String name;
    private String contact;
    private String planet;
    private int numberCS;
    private LocalDate dateLastPurchase;
    private List<Weapon> weapons = new ArrayList<>();
}
