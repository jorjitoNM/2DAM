package org.examen.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.examen.domain.service.MongoService;

public class Ex1 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        MongoService service = container.select(MongoService.class).get();
        service.loadDataIntoMysql();
    }
}
