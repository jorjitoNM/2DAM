package org.example;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;

public class Menu extends JOptionPane {
    public Menu() {
        super();
        this.initialValue = "Escoja una opcion antes de empezar:";
        this.messageType = QUESTION_MESSAGE;
    }
}
