package org.examen.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.examen.domain.errors.AppError;
import org.examen.domain.service.AnimalsService;

public class Ex1 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        AnimalsService service = container.select(AnimalsService.class).get();
        try {
            service.visit("Savannah", "Charlie Green");
        } catch (AppError e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
