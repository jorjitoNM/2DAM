package org.example.game.ahorcadoweb.game;

import jakarta.servlet.http.HttpSession;
import org.example.game.ahorcadoweb.common.Constantes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.concurrent.CompletionService;

@Controller
public class GameController {

    private static final String PALABRA = "avion";

    private static final String[] PARTES_AHORCADO = {
            "  O", " /", "|", "\\", " /", " \\"
    };

    @GetMapping(Constantes.GUESS_URL)
    public String doGet(HttpSession session, Model model) {
        char[] palabra = (char[]) session.getAttribute(Constantes.PALABRA);
        if (palabra != null) {
            palabra = " _ ".repeat(PALABRA.length()).toCharArray();
            session.setAttribute(Constantes.PALABRA, palabra);
            session.setAttribute(Constantes.INTENTOS, 0);
        }
        model.addAttribute(Constantes.PALABRA, palabra);
        return Constantes.GAME;
    }

    @PostMapping(Constantes.GUESS_URL)
    public String doPost(@RequestParam(Constantes.LETRA) String letra, HttpSession session, Model model) {
        if ((int) session.getAttribute(Constantes.INTENTOS) <= 5) {
            if (PALABRA.contains(letra)) {
                char[] palabra = addLetra((char[]) session.getAttribute(Constantes.PALABRA), letra.charAt(0));
                session.setAttribute(Constantes.PALABRA, palabra);
            } else {
                int intentos = (int) session.getAttribute(Constantes.INTENTOS);
                String letrasUsadas = (String) session.getAttribute(Constantes.LETRAS_USADAS);
                letrasUsadas += letra;
                session.setAttribute(Constantes.LETRAS_USADAS, letrasUsadas);
                model.addAttribute(Constantes.LETRAS_USADAS, letrasUsadas);
                session.setAttribute(Constantes.INTENTOS,  intentos+ 1);
                model.addAttribute(Constantes.INTENTOS, intentos + 1);
            }
            if (checkWin((char[]) session.getAttribute(Constantes.PALABRA))) {
                return Constantes.WIN;
            }
        }
        else {
            model.addAttribute(Constantes.LOSS, true);
        }
        model.addAttribute(Constantes.PROGRESO, construirAhorcado((int) session.getAttribute(Constantes.INTENTOS)));
        return Constantes.GAME;
    }

    private boolean checkWin(char[] palabra) {
        boolean exit = true;
        for (int i = 0; i < palabra.length && exit; i++) {
            if (palabra[i] != '_')
                exit = false;
        }
        return exit;
    }

    private char[] addLetra(char[] palabra, char letra) {
        for (int i = 0; i < palabra.length; i++) {
            if (palabra[i] == letra)
                palabra[i] = letra;
        }
        return palabra;
    }

    private String construirAhorcado(int intentos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intentos; i++) {
            sb.append(PARTES_AHORCADO[i]).append("\n");
        }
        return sb.toString();
    }

    @GetMapping(Constantes.RESET_URL)
    public RedirectView reset(HttpSession session) {
        session.invalidate();
        return new RedirectView(Constantes.GUESS_URL);
    }
}
