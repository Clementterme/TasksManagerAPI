package com.example.task.controller;

import com.example.task.Task;
import com.example.task.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskService taskService; // real TaskService object automatically injected in TaskController instance

    // On vide taskService avant chaque test comme on utilise la vraie instance de TaskService
    @BeforeEach
    void setup() {
        taskService.getTasks().clear();
    }

    @Test
    void shouldReturnTasks() throws Exception {

        taskService.ajouterTask(new Task("Faire les courses"));
        taskService.ajouterTask(new Task("Apprendre Spring Boot"));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].description").value("Faire les courses"))
                .andExpect(jsonPath("$[1].description").value("Apprendre Spring Boot"));
    }

    @Test
    void shouldReturnEmptyListWhenNoTaskExists() throws Exception {

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

}
