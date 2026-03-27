package com.example.task.service;

import com.example.task.Task;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private List<Task> tasks = new ArrayList<>();

    public void ajouterTask(Task task) {
        tasks.add(task);
    }

    public void supprimerTask(String id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void terminerTask(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.terminer();
            }
        }
    }
}
