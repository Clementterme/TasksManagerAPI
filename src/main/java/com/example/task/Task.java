package com.example.task;

import java.util.UUID;

public class Task {

    private final String id;
    private String description;
    private String etat;

    public Task(String description) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.etat = "en cours";
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void terminer() {
        this.etat = "terminé";
    }
}