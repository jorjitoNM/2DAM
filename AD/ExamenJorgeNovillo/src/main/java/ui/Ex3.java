package ui;

import domain.service.ZooService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        ZooService service = container.select(ZooService.class).get();
        Scanner sc = new Scanner(System.in);
        service.deleteAnimal("Nemo",false)
                .peek(ok -> System.out.println("Animal deleted successfully"))
                .peekLeft(error -> {
                    System.out.println(error.message() + ", type true if you want to delete it");
                    if (sc.nextLine().equalsIgnoreCase("true")) {
                        service.deleteAnimal("Nemo",true).peek(ok -> System.out.println("Animal deleted successfully"))
                                .peekLeft(error2 -> System.out.println(error.message()));
                    }
                });
    }
}
