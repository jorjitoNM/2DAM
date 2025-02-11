package ui;

import domain.service.Service;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Ex3 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        Service service = container.select(Service.class).get();
        service.saveBattle().peek(
                battle -> System.out.println(battle.toString())
        ).peekLeft(error -> System.out.println(error.message()));
    }
}
