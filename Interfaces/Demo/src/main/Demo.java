package main;

import componente_fecha.QDate;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Demo {

    public static void main(String[] args) {
        QDate fecha1 = new QDate();
        QDate fecha2 = new QDate();

        JPanel panelPrincipal = new JPanel(new GridLayout(3, 1, 0, 10));
        panelPrincipal.add(fecha1);
        panelPrincipal.add(fecha2);

        JLabel labelInfo = new JLabel("Seleccione fechas y pulse Calcular Fecha");
        labelInfo.setHorizontalAlignment(SwingConstants.CENTER);

        JButton botonCalcular = new JButton("Calcular Fecha");

        botonCalcular.addActionListener(e -> {
            try {
                LocalDate date1 = fecha1.getDate();
                LocalDate date2 = fecha2.getDate();
                long diasDiferencia = ChronoUnit.DAYS.between(date1, date2);

                labelInfo.setText("La diferencia es de " + Math.abs(diasDiferencia) + " días.");
            } catch (Exception ex) {
                labelInfo.setText("Error al calcular la diferencia.");
            }
        });

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(botonCalcular, BorderLayout.CENTER);
        panelInferior.add(labelInfo, BorderLayout.SOUTH);

        panelPrincipal.add(panelInferior);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panelPrincipal);
        frame.setSize(300, 250);
        frame.setLocation(550, 300);
        frame.setVisible(true);
    }
}
