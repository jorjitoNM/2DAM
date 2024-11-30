package com.example.gestortareas.ui;

import com.example.gestortareas.domain.model.Task;


public class AppContext {

    private static Task currentTask;

    public static Task getCurrentTask() {
        return currentTask;
    }

    public static void setCurrentTask(Task currentTask) {
        AppContext.currentTask = currentTask;
    }
}
