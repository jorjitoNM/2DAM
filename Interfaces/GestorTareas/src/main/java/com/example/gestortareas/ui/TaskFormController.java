package com.example.gestortareas.ui;


import com.example.gestortareas.domain.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;



public class TaskFormController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField description;

    private Task task;



    public TaskFormController(Task task) {
        this.task = task;
    }

    @FXML
    private void initialize() {
        if (task != null) {
            nameField.setText(task.getName());
            description.setText(task.getDescription());
        }
    }

    @FXML
    private void handleSave() {
        if (task != null) {
            task.setName(nameField.getText());
            task.setDescription(description.getText());
        }
    }

    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public TextField getDescriptionField() {
        return description;
    }

    public void setDescriptionField(TextField descriptionField) {
        this.description = descriptionField;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}


