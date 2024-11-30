package com.example.gestortareas.ui;

import com.example.gestortareas.domain.model.Task;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import java.util.List;

public class StatsController {
    @FXML
    private Label totalTasksLabel;
    @FXML
    private Label startedTasksLabel;
    @FXML
    private Label completedTasksLabel;
    @FXML
    private Label inProgressTasksLabel;
    @FXML
    private PieChart statsChart;

    @FXML
    private void initialize() {
        // Access the static task list from MainController
        List<Task> taskList = MainController.getTaskList();

        int total = taskList.size();
        int started = (int) taskList.stream().filter(t -> t.getCompletionPercentage() > 0).count();
        int completed = (int) taskList.stream().filter(t -> t.getCompletionPercentage() == 100).count();
        int inProgress = total - started - completed;

        totalTasksLabel.setText("Total: " + total);
        startedTasksLabel.setText("Iniciadas: " + started);
        completedTasksLabel.setText("Terminadas: " + completed);
        inProgressTasksLabel.setText("En ejecución: " + inProgress);

        statsChart.setData(FXCollections.observableArrayList(
                new PieChart.Data("Iniciadas", started),
                new PieChart.Data("Terminadas", completed),
                new PieChart.Data("En ejecución", inProgress)
        ));
    }

    public Label getTotalTasksLabel() {
        return totalTasksLabel;
    }

    public void setTotalTasksLabel(Label totalTasksLabel) {
        this.totalTasksLabel = totalTasksLabel;
    }

    public Label getStartedTasksLabel() {
        return startedTasksLabel;
    }

    public void setStartedTasksLabel(Label startedTasksLabel) {
        this.startedTasksLabel = startedTasksLabel;
    }

    public Label getCompletedTasksLabel() {
        return completedTasksLabel;
    }

    public void setCompletedTasksLabel(Label completedTasksLabel) {
        this.completedTasksLabel = completedTasksLabel;
    }

    public Label getInProgressTasksLabel() {
        return inProgressTasksLabel;
    }

    public void setInProgressTasksLabel(Label inProgressTasksLabel) {
        this.inProgressTasksLabel = inProgressTasksLabel;
    }

    public PieChart getStatsChart() {
        return statsChart;
    }

    public void setStatsChart(PieChart statsChart) {
        this.statsChart = statsChart;
    }
}


