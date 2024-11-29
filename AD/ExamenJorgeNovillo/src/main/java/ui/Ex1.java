package ui;

import domain.service.ZooService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Ex1 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        ZooService service = container.select(ZooService.class).get();
        service.loadXML("Bob Brown")
                .peek(animalVisits -> animalVisits.forEach(System.out::println))
                .peekLeft(error -> System.out.println(error.message()));

    }
}
