package org.examen.dao.mappers;

import org.examen.dao.model.FactionMongo;
import org.examen.domain.model.Faction;
import org.examen.domain.model.WeaponsFaction;

import java.util.List;

public class FactionMapper {

    public Faction toFaction(FactionMongo f) {
        return new Faction(f.getName(),f.getContact(),f.getPlanet(),f.getControlledSystems(),f.getLastPurchase());
    }

    public Faction toFaction(FactionMongo f, List<WeaponsFaction> weaponsFactions) {
        return new Faction(f.getName(),f.getContact(),f.getPlanet(),f.getControlledSystems(),f.getLastPurchase(),weaponsFactions);
    }
}
