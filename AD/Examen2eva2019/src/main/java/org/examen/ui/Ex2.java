package org.examen.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.examen.domain.model.Weapon;
import org.examen.domain.service.WeaponsService;

public class Ex2 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        WeaponsService service = container.select(WeaponsService.class).get();
        try {
            service.save(new Weapon( "Gun", 20.0));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
