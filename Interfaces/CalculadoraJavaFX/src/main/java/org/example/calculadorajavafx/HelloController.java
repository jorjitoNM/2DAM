package org.example.calculadorajavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloController {

    @FXML
    private TextField caja;

    private List<String> operandos;
    private List<String> operadores;
    private StringBuilder operacion;

    public HelloController() {
        operacion = new StringBuilder();
        operadores = new ArrayList<>();
        operandos = new ArrayList<>();
    }

    public void onNumberClicked(ActionEvent actionEvent) {
        Button numero = (Button) actionEvent.getSource();
        if (numero.getText().equals("CE") || numero.getText().equals("C")) {
            caja.clear();
        } else {
            operacion.append(numero.getText());
            caja.setText(operacion.toString());
        }
    }

    public void onEqualClicked() {
        if (caja.getCharacters().isEmpty())
            errorAlert();
        else {
            String operation = caja.getText();
            if (isOperator(caja.getCharacters().charAt(0))) {
                errorAlert();
            } else {
                parseCharacters(operation);
            }
        }
    }

    private void parseCharacters(String operation) {
        String[] trozos = operation.split("(?=[x%])");
        for (String trozo : trozos) {
            if (isOperator(trozo.charAt(0))) {
                operadores.add(String.valueOf(trozo.charAt(0)));
                trozo = trozo.substring(1);
            }
            operandos.add(trozo);
        }
        caja.setText(operate());
    }

    private String operate() {
        operandos = sumarOperandos(operandos);
        int resultado = 0;
        for (int i = 0; i < operadores.size(); i++) {
            if (operadores.get(i).equals("x")) {
                resultado = Integer.parseInt(operandos.get(i))*(Integer.parseInt(operandos.get(i+1)));
            }
            else
                resultado = Integer.parseInt(operandos.get(i))/(Integer.parseInt(operandos.get(i+1)));
        }
        operacion = new StringBuilder();
        operadores.clear();
        operandos.clear();
        return String.valueOf(resultado);
    }

    private List<String> sumarOperandos (List<String> op) {
        List<String> nuevosOperandos = new ArrayList<>();
        AtomicInteger resultado = new AtomicInteger();
        op.forEach(o -> {
            String [] numeros = o.split("(?=[+-])");
            for (String numero : numeros) {
                resultado.addAndGet(Integer.parseInt(numero));
            }
            nuevosOperandos.add(String.valueOf(resultado.get()));
        });
        return nuevosOperandos;
    }

    private void errorAlert() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText("Introduzca numeros para operar");
        alerta.showAndWait();
    }

    private boolean isOperator(char c) {
        return (c == 'x' || c == '%');
    }
}