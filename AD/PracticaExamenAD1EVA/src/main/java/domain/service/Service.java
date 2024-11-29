package domain.service;

import data.FactionsRepository;
import data.remote.FactionsJDBCRepository;
import domain.error.ErrorApp;
import domain.model.Battle;
import domain.model.Faction;
import domain.model.Weapon;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private final FactionsRepository factionsRepository;
    private final FactionsJDBCRepository factionsJDBCRepository;

    @Inject
    public Service(FactionsRepository factionsRepository, data.remote.FactionsJDBCRepository factionsJDBCRepository) {
        this.factionsRepository = factionsRepository;
        this.factionsJDBCRepository = factionsJDBCRepository;
    }

    public Either<ErrorApp, Void> loadXml() {
        return factionsRepository.loadXML()
                .flatMap(factionsJDBCRepository::saveFactions);
    }

    public Either<ErrorApp, Integer> countRebelsWeapons() {
        return factionsJDBCRepository.countRebelsWeapons(provideWeapons());
    }

    public Either<ErrorApp, Battle> saveBattle() {
        Faction faction1 = new Faction();
        if (faction1 == null)
            faction1 = new Faction("Gangsters", "+19 123 123 123", "Earth", 120, LocalDate.now(), provideWeapons());
        return factionsJDBCRepository.saveBattle(new Battle(14, "Fighting for fentanyl", faction1, faction1, "The park", LocalDate.now(), 13));
    }

    private List<Weapon> provideWeapons() {
        List<Weapon> weapons = new ArrayList<>();
        int i = 0;
        while (i < 10) {
            weapons.add(new Weapon(1, "Glock", 250));
            i++;
        }
        return weapons;
    }

    public Either<ErrorApp, Integer> updateWeaponPrice(Weapon weapon, int newPrice) {
        return factionsJDBCRepository.updateWeaponPrice(weapon, newPrice);
    }

    public Either<ErrorApp, String> getAll() {
        StringBuilder sb = new StringBuilder();
        return factionsJDBCRepository.getAllFactions().flatMap(factions -> {
                    factions.forEach(f -> sb.append(f.toString()).append("\n"));
                    return Either.right(sb.toString());
                }
        );
    }
}