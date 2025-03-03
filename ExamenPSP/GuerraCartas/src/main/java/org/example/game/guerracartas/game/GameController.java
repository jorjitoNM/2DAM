package com.peliculasbackendspring.game;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Controller
public class GameController {
    private static final String[] CARTAS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private static final int[] VALORES = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    @GetMapping("/cartas")
    public String inicio(HttpSession session, Model model) {
        if (session.getAttribute("mazoJugador1") == null) {
            List<String> mazo = generarMazo();
            Collections.shuffle(mazo);

            session.setAttribute("mazoJugador1", new ArrayList<>(mazo.subList(0, 26)));
            session.setAttribute("mazoJugador2", new ArrayList<>(mazo.subList(26, 52)));
            session.setAttribute("rondasGanadasJ1", 0);
            session.setAttribute("rondasGanadasJ2", 0);
            session.setAttribute("turno", 1);
            session.setAttribute("mensaje", "");
        }

        model.addAttribute("mensaje", session.getAttribute("mensaje"));
        model.addAttribute("turno", session.getAttribute("turno"));
        model.addAttribute("rondasJ1", session.getAttribute("rondasGanadasJ1"));
        model.addAttribute("rondasJ2", session.getAttribute("rondasGanadasJ2"));
        return "cartas";
    }

    @PostMapping("/cartas")
    public String jugarRonda(HttpSession session) {
        List<String> mazoJ1 = (List<String>) session.getAttribute("mazoJugador1");
        List<String> mazoJ2 = (List<String>) session.getAttribute("mazoJugador2");
        int rondasJ1 = (int) session.getAttribute("rondasGanadasJ1");
        int rondasJ2 = (int) session.getAttribute("rondasGanadasJ2");
        int turno = (int) session.getAttribute("turno");

        if (mazoJ1.isEmpty() || mazoJ2.isEmpty() || turno > 5) {
            session.setAttribute("mensaje", "¡Juego terminado! Resultado: Jugador 1 - " + rondasJ1 + " | Jugador 2 - " + rondasJ2);
            return "redirect:/cartas";
        }

        String cartaJ1 = mazoJ1.remove(0);
        String cartaJ2 = mazoJ2.remove(0);
        int valorJ1 = obtenerValor(cartaJ1);
        int valorJ2 = obtenerValor(cartaJ2);

        if (valorJ1 > valorJ2) {
            rondasJ1++;
            session.setAttribute("mensaje", "Ronda " + turno + ": Jugador 1 gana con " + cartaJ1 + " vs " + cartaJ2);
        } else if (valorJ2 > valorJ1) {
            rondasJ2++;
            session.setAttribute("mensaje", "Ronda " + turno + ": Jugador 2 gana con " + cartaJ2 + " vs " + cartaJ1);
        } else {
            session.setAttribute("mensaje", "Ronda " + turno + ": ¡Empate! " + cartaJ1 + " vs " + cartaJ2);
        }

        session.setAttribute("turno", turno + 1);
        session.setAttribute("rondasGanadasJ1", rondasJ1);
        session.setAttribute("rondasGanadasJ2", rondasJ2);
        return "redirect:/cartas";
    }

    @GetMapping("/reiniciar-cartas")
    public String reiniciar(HttpSession session) {
        session.invalidate();
        return "redirect:/cartas";
    }

    private List<String> generarMazo() {
        List<String> mazo = new ArrayList<>();
        for (String carta : CARTAS) {
            mazo.add(carta + "♠");
            mazo.add(carta + "♣");
            mazo.add(carta + "♥");
            mazo.add(carta + "♦");
        }
        return mazo;
    }

    private int obtenerValor(String carta) {
        String valor = carta.substring(0, carta.length() - 1);
        for (int i = 0; i < CARTAS.length; i++) {
            if (CARTAS[i].equals(valor)) return VALORES[i];
        }
        return 0;
    }
}