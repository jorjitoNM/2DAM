package com.peliculasbackendspring;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Arrays;


@Controller
public class TresEnRaya{

    @GetMapping("/juego")
    public String doGet(HttpSession session, Model model) {
        String[] board = (String[]) session.getAttribute("board");
        if (board == null) {
            board = new String[9];
            Arrays.fill(board, "");
            session.setAttribute("board", board);
            session.setAttribute("jugadorActual", "Diego");
        }
        model.addAttribute("board", board);
        model.addAttribute("jugadorActual", session.getAttribute("jugadorActual"));
        return "juego";
    }



    @PostMapping("/juego")
    public String doPost(@RequestParam("cell") int cell, HttpSession session, Model model) {
        String[] board = (String[]) session.getAttribute("board");
        String jugadorActual = (String) session.getAttribute("jugadorActual");

        if (board[cell].isEmpty()) {
            board[cell] = jugadorActual;

            jugadorActual = "Diego".equals(jugadorActual) ? "Dani" : "Diego";

            session.setAttribute("board", board);
            session.setAttribute("jugadorActual", jugadorActual);
        }
        model.addAttribute("board", board);
        model.addAttribute("jugadorActual", jugadorActual);
        return "juego";
    }


    @PostMapping("/juego/reset")
    public String reset(HttpSession session) {
        session.invalidate();
        return "redirect:/juego";
    }



}

