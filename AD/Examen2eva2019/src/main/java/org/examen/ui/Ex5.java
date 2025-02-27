package org.examen.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.examen.domain.model.Weapon;
import org.examen.domain.model.WeaponsFaction;
import org.examen.domain.service.FactionsService;
import org.examen.domain.service.WeaponsService;

import java.util.ArrayList;
import java.util.List;

public class Ex5 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        WeaponsService weaponsService = container.select(WeaponsService.class).get();
        FactionsService factionsService = container.select(FactionsService.class).get();
        try {
            weaponsService.getAll().forEach(System.out::println);
            System.out.println("------------------------");
            factionsService.getAll().forEach(System.out::println);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
