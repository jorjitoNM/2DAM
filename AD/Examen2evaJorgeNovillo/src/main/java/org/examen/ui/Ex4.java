package org.examen.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.examen.domain.errors.AppError;
import org.examen.domain.service.MongoAggregationService;

public class Ex4 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        MongoAggregationService service = container.select(MongoAggregationService.class).get();
        try {
            System.out.println("The number of tickets: ");
            service.query1().forEach(System.out::println);
            System.out.println("The most visited animal: ");
            service.query2().forEach(System.out::println);
        } catch (AppError e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
