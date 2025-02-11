package ui;

import domain.service.Service;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Ex2 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        Service service = container.select(Service.class).get();
        service.countRebelsWeapons().peek(response -> System.out.println( "Aliocias´s brother counted " + response + " weapons of the rebels"))
                .peekLeft(error -> System.out.println(error.message()));

    }
}
