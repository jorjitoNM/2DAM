package org.examen.domain.service;

import jakarta.inject.Inject;
import org.examen.dao.repository.mysql.FactionsRepository;
import org.examen.dao.repository.mysql.WeaponsRepository;
import org.examen.domain.model.Faction;
import org.examen.domain.model.Weapon;
import org.examen.domain.model.WeaponsFaction;

import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

public class WeaponsService {

    private final WeaponsRepository weaponsRepository;
    private final FactionsRepository factionsRepository;

    @Inject
    public WeaponsService(WeaponsRepository weaponsRepository, FactionsRepository factionsRepository) {
        this.weaponsRepository = weaponsRepository;
        this.factionsRepository = factionsRepository;
    }

    public void save (Weapon weapon) {
        Faction rebels = factionsRepository.get("Rebels");
        List<WeaponsFaction> weaponsFactionList = listOf(new WeaponsFaction(rebels));
        weapon.setWeaponsFactions(weaponsFactionList);
        weaponsRepository.save(weapon);
    }

    public void update(Weapon weapon) {
        weaponsRepository.update(weapon);
    }

    public List<Weapon> getAll() {
        return weaponsRepository.getAll();
    }

    public List<Weapon> getAll(String factionName) {
        return weaponsRepository.getAll(factionName);
    }
}
