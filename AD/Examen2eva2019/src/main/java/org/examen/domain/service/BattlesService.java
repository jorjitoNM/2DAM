package org.examen.domain.service;

import jakarta.inject.Inject;
import org.examen.dao.repository.mysql.BattlesRepository;
import org.examen.domain.model.Battle;

public class BattlesService {
    private final BattlesRepository battlesRepository;

    @Inject
    public BattlesService(BattlesRepository battlesRepository) {
        this.battlesRepository = battlesRepository;
    }

    public void save(Battle battle) {
        battlesRepository.save(battle);
    }
}
