package ui;

import domain.model.Grupo;
import domain.model.Mensaje;
import utilities.security.Asymmetric;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Ex1 {

    public static void main(String[] args) {
        final List<Mensaje> mensajes = new ArrayList<>();

        final Asymmetric asymmetric = new Asymmetric();

        final Grupo grupo1 = new Grupo("Grupo1", "1234");
        final Grupo grupo2 = new Grupo("Grupo2", "4321");

        final String nombreJefe = "juan";
        AtomicReference<KeyPair> keyPair = new AtomicReference<>();
        asymmetric.saveUserKeys(nombreJefe)
                .peek(keyPair::set)
                .peekLeft(System.out::println);
        PublicKey jefePublicKey = keyPair.get().getPublic();
        PrivateKey jefePrivateKey = keyPair.get().getPrivate();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println("Introduzca el nombre de su grupo");
            String groupName = sc.nextLine();
            if (groupName.equals(grupo1.getNombre()) || groupName.equals(grupo2.getNombre())) {
                System.out.println("Introduzca la contraseña de su grupo");
                String groupPassword = sc.nextLine();
                if (groupName.equals(grupo1.getNombre()) && groupPassword.equals(grupo1.getContraseña()) || groupName.equals(grupo2.getNombre()) && groupPassword.equals(grupo2.getContraseña())) {
                    System.out.println("1. Mandar un mensaje\n2.Leer mensajes\n3.Salir (escribe exit)");
                    String respuesta = sc.nextLine();
                    if (respuesta.equals("exit")) {
                        exit = true;
                    } else if (respuesta.equals("1")) {
                        System.out.println("Introduzca la fecha");
                        String fecha = sc.nextLine();
                        asymmetric.signMessage(new Mensaje(fecha, groupName), jefePrivateKey)
                                .peek(mensaje -> {
                                    mensajes.add(mensaje);
                                    System.out.println("Mensaje añadido con exito");
                                })
                                .peekLeft(System.out::println);
                    } else {
                        if (mensajes.stream().filter(m -> m.getGroupName().equals(groupName)).toList().isEmpty()) {
                            System.out.println("No hay mensajes en este grupo");
                        } else {
                            mensajes.stream().filter(m -> m.getGroupName().equals(groupName))
                                    .forEach(mensaje -> asymmetric.checkMessageSign(mensaje, jefePublicKey)
                                            .peek(ok -> System.out.println(mensaje))
                                            .peekLeft(System.out::println));
                        }
                    }
                }
            }
        } while (!exit);
    }

}
