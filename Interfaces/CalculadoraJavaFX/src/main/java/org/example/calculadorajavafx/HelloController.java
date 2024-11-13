package org.example.calculadorajavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.List;

public class HelloController {

    @FXML
    private TextField caja;

    private List<String> operandos;

    public void onNumberClicked(ActionEvent actionEvent) {
        Button numero = (Button) actionEvent.getSource();

    }
    public void onEqualClicked(ActionEvent actionEvent) {
        if (caja.getCharacters().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Introduzca numeros para operar");
            alerta.showAndWait();
        }
        else {
            String operation = caja.getText();
            for (int i = 0; i < operation.length(); i++) {
                while (isOperator(operation.charAt(i))) {

                }
            }
        }
    }

    private boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/') {
            return true;
        }
    }
}