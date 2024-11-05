package component;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        JPanel panel = new QDate();
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
