package com.example.apptareas.controllers;

import com.example.apptareas.models.Task;
import com.example.apptareas.utils.FileManager;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.util.List;

public class StatsController {

    @FXML
    private PieChart statsChart;

    @FXML
    public void initialize() {
        List<Task> tasks = FileManager.loadTasks();
        long total = tasks.size();
        long completed = tasks.stream().filter(t -> t.getProgress() == 100).count();
        long inProgress = tasks.stream().filter(t -> t.getProgress() > 0 && t.getProgress() < 100).count();
        long notStarted = tasks.stream().filter(t -> t.getProgress() == 0).count();

        statsChart.getData().addAll(
                new PieChart.Data("Completadas", completed),
                new PieChart.Data("En progreso", inProgress),
                new PieChart.Data("No iniciadas", notStarted)
        );
    }
}

