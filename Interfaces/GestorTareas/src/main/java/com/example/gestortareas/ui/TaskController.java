package com.example.gestortareas.ui;

import com.example.gestortareas.domain.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskController {

    @FXML
    private TableView<Task> taskTable;

    public TableView<Task> getTaskTable() {
        return taskTable;
    }

    public void setTaskTable(TableView<Task> taskTable) {
        this.taskTable = taskTable;
    }

    @FXML
    private void handleEditTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            try {
                // Carga el archivo FXML del formulario de tareas
                FXMLLoader loader = new FXMLLoader(getClass().getResource("task_form.fxml"));
                Parent root = loader.load();

                // Obtén el controlador del formulario y pásale la tarea seleccionada
                TaskFormController controller = loader.getController();
                controller.setTask(selectedTask);

                // Cambia a la escena del formulario de tareas
                Stage stage = (Stage) taskTable.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

