package ui;

import domain.model.Weapon;
import domain.service.Service;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        Service service = container.select(Service.class).get();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, write the new weapon value");
        try {
            service.updateWeaponPrice(new Weapon(), Integer.parseInt(sc.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Please enter numbers only");
        }
    }
}
