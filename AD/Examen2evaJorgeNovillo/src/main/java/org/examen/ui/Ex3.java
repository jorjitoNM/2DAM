package org.examen.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.examen.domain.errors.AppError;
import org.examen.domain.service.MongoAnimalVisitsService;

import java.time.LocalDate;

public class Ex3 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        MongoAnimalVisitsService service = container.select(MongoAnimalVisitsService.class).get();
        try {
            service.update("Charlie Green","Nemo", LocalDate.of(2025,2,15));
        } catch (AppError e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
