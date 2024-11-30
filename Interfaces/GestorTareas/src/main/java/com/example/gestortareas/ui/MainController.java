package com.example.gestortareas.ui;

import com.example.gestortareas.domain.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;



import java.io.IOException;

public class MainController {
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private Button createTaskButton;
    @FXML
    private Button editTaskButton;
    @FXML
    private Button statsButton;

    public TableView<Task> getTaskTable() {
        return taskTable;
    }

    public void setTaskTable(TableView<Task> taskTable) {
        this.taskTable = taskTable;
    }

    public Button getCreateTaskButton() {
        return createTaskButton;
    }

    public void setCreateTaskButton(Button createTaskButton) {
        this.createTaskButton = createTaskButton;
    }

    public Button getEditTaskButton() {
        return editTaskButton;
    }

    public void setEditTaskButton(Button editTaskButton) {
        this.editTaskButton = editTaskButton;
    }

    public Button getStatsButton() {
        return statsButton;
    }

    public void setStatsButton(Button statsButton) {
        this.statsButton = statsButton;
    }

    public static ObservableList<Task> getTaskList() {
        return taskList;
    }

    public static void setTaskList(ObservableList<Task> taskList) {
        MainController.taskList = taskList;
    }

    // Static getter for the task list
    // Static task list

    private static ObservableList<Task> taskList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Bind the task list to the table
        taskTable.setItems(taskList);
    }

    @FXML
    private void handleCreateTask() {
        try {
            App.setRoot("task_form");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleEditTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            try {
                App.setRoot("task_form");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void handleStats() {
        try {
            App.setRoot("stats");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


