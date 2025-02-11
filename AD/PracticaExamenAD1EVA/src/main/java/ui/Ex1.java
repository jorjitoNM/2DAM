package ui;

import domain.service.Service;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Ex1 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        Service service = container.select(Service.class).get();

        service.loadXml().peek(ok -> System.out.println("XMl cargados con exito en base de datos"))
                .peekLeft(error -> System.out.println(error.message()));


    }
}
