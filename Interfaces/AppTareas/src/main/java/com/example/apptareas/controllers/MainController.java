package com.example.apptareas.controllers;

import com.example.apptareas.models.Task;
import com.example.apptareas.utils.FileManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private ListView<Task> taskListView;

    private List<Task> tasks;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        if (tasks == null) {
            tasks = FXCollections.observableArrayList();  // Asegúrate de que no sea null
        }

        // Aquí, agregas tus tareas o las recuperas de una fuente de datos
        tasks.add(new Task("Tarea 1"));
        tasks.add(new Task("Tarea 2"));

        // Si tienes algún otro componente que use esta lista, como un ListView:
        // listView.setItems(tareas);
    }

    @FXML
    public void addTask() {
        loadTaskForm(null);
    }

    @FXML
    public void editTask() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            loadTaskForm(selectedTask);
        } else {
            showAlert("Error", "Por favor, seleccione una tarea.");
        }
    }

    @FXML
    public void viewStats() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/stats.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTaskForm(Task task) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/task_form.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            TaskController controller = loader.getController();
            controller.setTask(task);
            stage.showAndWait();
            refreshTaskList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshTaskList() {
        taskListView.getItems().clear();
        tasks = FileManager.loadTasks();
        taskListView.getItems().addAll(tasks);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

