package com.peliculasbackendspring.game;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {
    @GetMapping("/rps")
    public String inicio(HttpSession session, Model model) {
        if (session.getAttribute("jugadas") == null) {
            session.setAttribute("jugadas", new String[2]);
            session.setAttribute("turno", 0);
        }
        model.addAttribute("turno", (int) session.getAttribute("turno") + 1);
        return "rps";
    }

    @PostMapping("/rps")
    public String jugar(@RequestParam("opcion") String opcion, HttpSession session) {
        String[] jugadas = (String[]) session.getAttribute("jugadas");
        int turno = (int) session.getAttribute("turno");

        jugadas[turno] = opcion;
        session.setAttribute("turno", turno + 1);

        if (turno == 1) {
            // Determinar ganador
            String resultado = calcularGanador(jugadas[0], jugadas[1]);
            session.setAttribute("resultado", resultado);
        }
        return "redirect:/rps";
    }

    @GetMapping("/reiniciar-rps")
    public String reiniciar(HttpSession session) {
        session.invalidate();
        return "redirect:/rps";
    }

    private String calcularGanador(String jugador1, String jugador2) {
        if (jugador1.equals(jugador2)) return "¡Empate!";
        if ((jugador1.equals("piedra") && jugador2.equals("tijeras")) ||
                (jugador1.equals("papel") && jugador2.equals("piedra")) ||
                (jugador1.equals("tijeras") && jugador2.equals("papel"))) {
            return "¡Jugador 1 gana!";
        }
        return "¡Jugador 2 gana!";
    }
}