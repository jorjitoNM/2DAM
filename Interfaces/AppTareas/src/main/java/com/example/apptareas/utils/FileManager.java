package com.example.apptareas.utils;

import com.example.apptareas.models.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String FILE_NAME = "tasks.json";
    private static final Gson gson = new Gson();

    public static List<Task> loadTasks() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            return gson.fromJson(reader, new TypeToken<List<Task>>() {}.getType());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void saveTask(Task task) {
        List<Task> tasks = loadTasks();
        tasks.removeIf(t -> t.getName().equals(task.getName()));
        tasks.add(task);
        saveTasks(tasks);
    }

    public static void saveTasks(List<Task> tasks) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(tasks, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


