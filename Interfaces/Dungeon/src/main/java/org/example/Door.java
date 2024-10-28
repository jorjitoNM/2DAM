package org.example;

import javax.swing.*;

public abstract class Door extends JButton {
    private String destination;

    public Door() {
    }

    public String getDestination() {
        return destination;
    }
}
