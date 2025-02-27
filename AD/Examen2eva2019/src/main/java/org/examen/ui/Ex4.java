package org.examen.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.examen.domain.model.Weapon;
import org.examen.domain.model.WeaponsFaction;
import org.examen.domain.service.WeaponsService;

import java.util.ArrayList;
import java.util.List;

public class Ex4 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        WeaponsService service = container.select(WeaponsService.class).get();
        try {
            double price = 600.17;
            int weaponId = 2;
            String name = "Arma actualizada";
            List<WeaponsFaction> weaponsFactionList = new ArrayList<>();
            Weapon w = new Weapon(weaponId,name,price,weaponsFactionList);
            service.update(w);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
