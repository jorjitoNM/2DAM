package org.examen.domain.service;

import jakarta.inject.Inject;
import org.examen.dao.mappers.FactionMapper;
import org.examen.dao.model.FactionMongo;
import org.examen.dao.repository.mongo.MongoRepository;
import org.examen.dao.repository.mysql.FactionsRepository;
import org.examen.dao.repository.mysql.WeaponFactionRepository;
import org.examen.dao.repository.mysql.WeaponsRepository;
import org.examen.domain.model.Faction;
import org.examen.domain.model.Weapon;
import org.examen.domain.model.WeaponsFaction;

import java.util.ArrayList;
import java.util.List;

public class MongoService {
    private final MongoRepository repository;
    private final FactionMapper mapper;
    private final FactionsRepository factionsRepository;
    private final WeaponsRepository weaponsRepository;
    private final WeaponFactionRepository weaponFactionRepository;

    @Inject
    public MongoService(MongoRepository repository, FactionMapper mapper, FactionsRepository factionsRepository, WeaponsRepository weaponsRepository, WeaponFactionRepository weaponFactionRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.factionsRepository = factionsRepository;
        this.weaponsRepository = weaponsRepository;
        this.weaponFactionRepository = weaponFactionRepository;
    }

    public boolean loadDataIntoMysql () {
        List<FactionMongo> mongoFactions = repository.loadDataFromMongo().getFirst().getFactions();
        List<Faction> factions = new ArrayList<>();
        List<Weapon> weapons = new ArrayList<>();
        List<WeaponsFaction> weaponsFactions = new ArrayList<>();
        for (FactionMongo mongoFaction : mongoFactions) {
            Faction f = mapper.toFaction(mongoFaction);
            if (mongoFaction.getWeapons() != null) {
                for (int j = 0; j < mongoFaction.getWeapons().size(); j++) {
                    Weapon w = mongoFaction.getWeapons().get(j);
                    WeaponsFaction wf = new WeaponsFaction(f, w);
                    weaponsFactions.add(wf);
                    weapons.add(w);
                }
            }
            factions.add(f);
        }
        factionsRepository.saveAll(factions);
        weaponsRepository.saveAll(weapons);
        weaponFactionRepository.saveAll(weaponsFactions);
        return true;
    }
}
