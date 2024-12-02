package com.example.apptareas.controllers;

import com.example.apptareas.models.Task;
import com.example.apptareas.utils.FileManager;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TaskController {

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField responsibleField;

    @FXML
    private Slider progressSlider;

    private Task task;

    @FXML
    public void saveTask() {
        if (task == null) {
            task = new Task();
        }

        task.setName(nameField.getText());
        task.setDescription(descriptionField.getText());
        task.setLocation(locationField.getText());
        task.setResponsible(responsibleField.getText());
        task.setProgress((int) progressSlider.getValue());

        FileManager.saveTask(task);
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    public void setTask(Task task) {
        this.task = task;
        if (task != null) {
            nameField.setText(task.getName());
            descriptionField.setText(task.getDescription());
            locationField.setText(task.getLocation());
            responsibleField.setText(task.getResponsible());
            progressSlider.setValue(task.getProgress());
        }
    }
}

