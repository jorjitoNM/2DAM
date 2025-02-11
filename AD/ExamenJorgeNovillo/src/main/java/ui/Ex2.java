package ui;

import domain.model.Visitor;
import domain.service.ZooService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.ArrayList;
import java.util.List;

public class Ex2 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        ZooService service = container.select(ZooService.class).get();
        List<String> animal = new ArrayList<>();
        animal.add("Parrot");
        animal.add("Elephant");
        service.saveInfo(new Visitor("David Johnson","david.johnson@example.com",3),animal)
                .peek(ok -> System.out.println("Information added successfully"))
                .peekLeft(error -> System.out.println(error.message()));


    }
}
