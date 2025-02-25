package org.examen.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.examen.domain.model.Battle;
import org.examen.domain.model.Faction;
import org.examen.domain.model.Spy;
import org.examen.domain.service.BattlesService;

import java.time.LocalDate;
import java.util.ArrayList;

public class Ex3 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        BattlesService service = container.select(BattlesService.class).get();
        try {
            Faction f1 = new Faction("Jorge","Jorge Novillo","Earth",6, LocalDate.now());
            Faction f2 = new Faction("Rebels","dsa","dsa",6, LocalDate.now());
            Spy spy = new Spy("luke","black",new ArrayList<>());
            service.save(new Battle("Batalla de trafalgar",f1,f2,"Madrid",LocalDate.now(),spy));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
