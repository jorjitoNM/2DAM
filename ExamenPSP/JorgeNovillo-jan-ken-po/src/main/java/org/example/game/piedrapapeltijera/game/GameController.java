package org.example.game.piedrapapeltijera.game;

import jakarta.servlet.http.HttpSession;
import org.example.game.piedrapapeltijera.common.Constantes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Objects;

@Controller
public class GameController {
    @GetMapping(Constantes.GAME_URL)
    public String getJuego(HttpSession session, Model model) {
        String jugadorActual = (String) session.getAttribute(Constantes.JUGADOR_ACTUAL);
        String jugadorQueApuesta;
        if (jugadorActual.equals(session.getAttribute(Constantes.JUGADOR_1))) {
            jugadorQueApuesta = Constantes.JUGADOR_1;
        } else {
            jugadorQueApuesta = Constantes.JUGADOR_2;
        }
        int ronda = (int) session.getAttribute(Constantes.RONDA + "_" + jugadorQueApuesta);
        int rondas = (int) session.getAttribute(Constantes.RONDAS);
        model.addAttribute(Constantes.JUGADOR_ACTUAL, jugadorActual);
        model.addAttribute(Constantes.RONDAS, rondas);
        model.addAttribute(Constantes.RONDA_ACTUAL, ronda);
        return Constantes.GAME;
    }

    @PostMapping(Constantes.GAME_URL)
    public String jugar(@RequestParam(Constantes.OPCION) String opcion, HttpSession session, Model model) {
        String jugadorActual = (String) session.getAttribute(Constantes.JUGADOR_ACTUAL);
        String jugadorQueApuesta;
        String jugadorQueNo;
        if (jugadorActual.equals(session.getAttribute(Constantes.JUGADOR_1))) {
            jugadorQueApuesta = Constantes.JUGADOR_1;
            jugadorQueNo = Constantes.JUGADOR_2;
        } else {
            jugadorQueApuesta = Constantes.JUGADOR_2;
            jugadorQueNo = Constantes.JUGADOR_1;
        }
        String[] jugadas = (String[]) session.getAttribute(Constantes.APUESTAS + "_" + jugadorQueApuesta);
        String[] jugadasOtro = (String[]) session.getAttribute(Constantes.APUESTAS + "_" + jugadorQueNo);
        int ronda = (int) session.getAttribute(Constantes.RONDA + "_" + jugadorQueApuesta);
        int rondas = (int) session.getAttribute(Constantes.RONDAS);
        if (ronda < rondas) {
            jugadas[ronda] = opcion;
            session.setAttribute(Constantes.APUESTAS + "_" + jugadorQueApuesta, jugadas);
        }
        if (Arrays.stream(jugadasOtro).noneMatch(Objects::isNull) && Arrays.stream(jugadas).noneMatch(Objects::isNull)) {
            model.addAttribute(Constantes.COMPROBAR, true);
        }
        ronda++;
        model.addAttribute(Constantes.JUGADOR_ACTUAL, jugadorActual);
        model.addAttribute(Constantes.RONDAS, rondas);
        model.addAttribute(Constantes.RONDA_ACTUAL, ronda);
        session.setAttribute(Constantes.RONDA + "_" + jugadorQueApuesta, ronda);
        return Constantes.GAME;
    }


    @GetMapping(Constantes.APUESTA_URL)
    public String calcularGanador(HttpSession session, Model model) {
        String[] apuestas1 = (String[]) session.getAttribute(Constantes.APUESTAS_1);
        String[] apuestas2 = (String[]) session.getAttribute(Constantes.APUESTAS_2);
        int puntosA = 0;
        int puntosB = 0;
        for (int i = 0; i < apuestas1.length; i++) {
            String apuesta1 = apuestas1[i];
            String apuesta2 = apuestas2[i];
            if (!apuesta1.equals(apuesta2)) {
                if ((apuesta1.equals(Constantes.PIEDRA) && apuesta2.equals(Constantes.TIJERA)) ||
                        (apuesta1.equals(Constantes.PAPEL) && apuesta2.equals(Constantes.PIEDRA)) ||
                        (apuesta1.equals(Constantes.TIJERA) && apuesta2.equals(Constantes.PAPEL))) {
                    puntosA++;
                } else
                    puntosB++;
            }
        }
        String vencedor = Constantes.EMPATE;
        if (puntosA > puntosB)
            vencedor = (String) session.getAttribute(Constantes.JUGADOR_2);
        else if (puntosB > puntosA)
            vencedor = (String) session.getAttribute(Constantes.JUGADOR_1);
        model.addAttribute(Constantes.VENCEDOR, vencedor);
        return Constantes.WIN;
    }


    @PostMapping(Constantes.CHECK_INICIO_URL)
    public String checkInicio(@RequestParam(Constantes.JUGADOR_1) String jugador1,
                              @RequestParam(Constantes.JUGADOR_2) String jugador2,
                              @RequestParam(Constantes.RONDAS) String rondas,
                              HttpSession session) {
        int rondasInt = Integer.parseInt(rondas);
        if (jugador1 != null || jugador2 != null || rondasInt >= 1) {
            session.setAttribute(Constantes.JUGADOR_1, jugador1);
            session.setAttribute(Constantes.JUGADOR_2, jugador2);
            session.setAttribute(Constantes.RONDAS, rondasInt);
            session.setAttribute(Constantes.JUGADOR_ACTUAL, jugador1);
            session.setAttribute(Constantes.APUESTAS_1, new String[rondasInt]);
            session.setAttribute(Constantes.APUESTAS_2, new String[rondasInt]);
            session.setAttribute(Constantes.RONDA_1, 0);
            session.setAttribute(Constantes.RONDA_2, 0);
            return "redirect:" + Constantes.GAME_URL;
        } else {
            return Constantes.ERROR;
        }
    }

    @GetMapping(Constantes.INICIO_URL)
    public String inicio() {
        return Constantes.INICIO;
    }

    @GetMapping(Constantes.CAMBIAR_URL)
    public String cambiar(HttpSession session) {
        String jugadorActual = (String) session.getAttribute(Constantes.JUGADOR_ACTUAL);
        if (jugadorActual.equals(session.getAttribute(Constantes.JUGADOR_1)))
            jugadorActual = Constantes.JUGADOR_2;
        else
            jugadorActual = Constantes.JUGADOR_1;
        session.setAttribute(Constantes.JUGADOR_ACTUAL, jugadorActual);
        return "redirect:" + Constantes.GAME_URL;
    }
}