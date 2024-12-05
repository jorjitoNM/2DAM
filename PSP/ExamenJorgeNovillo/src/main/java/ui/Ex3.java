package ui;

import domain.model.Apuesta;
import domain.model.Usuario;
import utilities.security_ex3.Symmetric;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex3 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final Usuario usuario = new Usuario();
        final Symmetric symmetric = new Symmetric();
        final List<String> apuestas = new ArrayList<>();

        System.out.println("Introduzca su nombre de usuario");
        String userName = sc.nextLine();
        System.out.println("Introduzca su contraseña");
        String password = sc.nextLine();
        if (userName.equals(usuario.getName()) && password.equals(usuario.getPassword())) {
            System.out.println("Introduzca la contraseña secreta del casino");
            String secretPasword = sc.nextLine();
            System.out.println("Introduzca el numero al que quiere apostar");
            int numero = Integer.parseInt(sc.nextLine());
            System.out.println("Intrdoduzca la cantidad");
            int cantidad = Integer.parseInt(sc.nextLine());
            symmetric.cipher(new Apuesta(numero, userName, cantidad), secretPasword)
                    .peek(ok -> {
                        apuestas.add(ok);
                        System.out.println(ok);
                        symmetric.decipher(ok,secretPasword)
                                        .peek(System.out::println)
                                                .peekLeft(System.out::println);
                    })
                    .peekLeft(System.out::println);
        }
    }
}