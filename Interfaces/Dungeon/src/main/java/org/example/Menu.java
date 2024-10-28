package org.example;

import javax.swing.*;

public class Menu extends JOptionPane {
    public Menu() {
        super();
        this.initialValue = "Escoja una opcion antes de empezar:";
        this.messageType = QUESTION_MESSAGE;
    }
}
