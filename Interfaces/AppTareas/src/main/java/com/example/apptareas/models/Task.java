package com.example.apptareas.models;

public class Task {
    private String name;
    private String description;
    private int progress;
    private String location;
    private String responsible;

    public Task() {}

    public Task(String name, String description, int progress, String location, String responsible) {
        this.name = name;
        this.description = description;
        this.progress = progress;
        this.location = location;
        this.responsible = responsible;
    }

    public Task(String s) {
        this.name = s;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    @Override
    public String toString() {
        return name + " (" + progress + "% completado)";
    }
}
