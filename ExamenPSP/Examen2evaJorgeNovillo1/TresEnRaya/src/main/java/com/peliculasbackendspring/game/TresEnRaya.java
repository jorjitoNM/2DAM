package com.peliculasbackendspring.game;

import com.peliculasbackendspring.common.Constantes;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


import java.util.Arrays;


@Controller
public class TresEnRaya {

    @GetMapping(Constantes.DO_URL)
    public String doGet(HttpSession session, Model model) {
        String[] board = (String[]) session.getAttribute(Constantes.BOARD);
        if (board == null) {
            board = new String[9];
            Arrays.fill(board, "");
            session.setAttribute(Constantes.JUGADOR_ACTUAL, Constantes.JUGADOR_X);
            session.setAttribute(Constantes.BOARD, board);
        }
        model.addAttribute(Constantes.BOARD, board);
        model.addAttribute(Constantes.JUGADOR_ACTUAL, session.getAttribute(Constantes.JUGADOR_ACTUAL));
        model.addAttribute(Constantes.WIN_MESSAGE, false);
        return Constantes.GAME;
    }


    @PostMapping(Constantes.DO_URL)
    public String doPost(@RequestParam(Constantes.CELL) int cell, HttpSession session, Model model) {
        String[] board = (String[]) session.getAttribute(Constantes.BOARD);
        String jugadorActual = (String) session.getAttribute(Constantes.JUGADOR_ACTUAL);
        if (board[cell].isEmpty()) {
            board[cell] = jugadorActual;
            if (checkWin(board, jugadorActual)) {
                return Constantes.WIN;
            }
            if (!Arrays.asList(board).contains(""))
                model.addAttribute(Constantes.NO_SPACE, true);
            jugadorActual = Constantes.JUGADOR_X.equals(jugadorActual) ? Constantes.JUGADOR_O : Constantes.JUGADOR_X;
        }
        session.setAttribute(Constantes.BOARD, board);
        session.setAttribute(Constantes.JUGADOR_ACTUAL, jugadorActual);
        model.addAttribute(Constantes.BOARD,board);
        model.addAttribute(Constantes.JUGADOR_ACTUAL, jugadorActual);
        return Constantes.GAME;
    }

    private boolean checkWin(String[] board, String jugadorActual) {
        boolean win = false;
        if (board[0].equals(jugadorActual) && board[3].equals(jugadorActual) && (board[6].equals(jugadorActual)))
            win = true;
        else if (board[0].equals(jugadorActual) && board[4].equals(jugadorActual) && (board[8].equals(jugadorActual)))
            win = true;
        else if (board[1].equals(jugadorActual) && board[4].equals(jugadorActual) && (board[7].equals(jugadorActual)))
            win = true;
        else if (board[0].equals(jugadorActual) && board[1].equals(jugadorActual) && (board[2].equals(jugadorActual)))
            win = true;
        else if (board[2].equals(jugadorActual) && board[4].equals(jugadorActual) && (board[8].equals(jugadorActual)))
            win = true;
        else if (board[3].equals(jugadorActual) && board[4].equals(jugadorActual) && (board[5].equals(jugadorActual)))
            win = true;
        else if (board[6].equals(jugadorActual) && board[7].equals(jugadorActual) && (board[8].equals(jugadorActual)))
            win = true;
        else if (board[2].equals(jugadorActual) && board[4].equals(jugadorActual) && (board[6].equals(jugadorActual)))
            win = true;
        return win;
    }


    @GetMapping(Constantes.RESET_URL)
    public RedirectView reset(HttpSession session) {
        session.invalidate();
        return new RedirectView(Constantes.DO_URL);
    }
}

