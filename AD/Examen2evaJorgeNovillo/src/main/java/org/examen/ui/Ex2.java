package org.examen.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.examen.domain.errors.AnimalHashVisits;
import org.examen.domain.errors.AppError;
import org.examen.domain.service.AnimalsService;

import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        AnimalsService service = container.select(AnimalsService.class).get();
        try {
            service.delete("Nemo", false);
        } catch (AnimalHashVisits e) {
            System.out.println("The animal has visits saved, type true to delete them");
            Scanner sc = new Scanner(System.in);
            String respuesta = sc.nextLine();
            if (respuesta.equals("true")) {
                service.delete("Nemo", true);
            }
        } catch (AppError e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
