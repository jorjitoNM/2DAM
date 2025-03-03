package com.peliculasbackendspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PeliculasBackendSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeliculasBackendSpringApplication.class, args);
        openBrowser("http://localhost:8080/juego");
    }

    private static void openBrowser(String url) {
            String os = System.getProperty("os.name").toLowerCase();
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
