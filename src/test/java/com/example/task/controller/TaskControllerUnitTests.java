package com.example.task.controller;

import com.example.task.Task;
import com.example.task.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerUnitTests {
    @Autowired
    private MockMvc mockMvc; //Object used to make HTTP request on our API

    @MockitoBean
    private TaskService taskService; //Mock object TaskService automatically injected in TaskController instance

    @Test
    void hello_should_return_message() throws Exception {

        Task task1 = new Task("Faire les courses");
        Task task2 = new Task("Apprendre Spring Boot");

        Mockito.when(taskService.getTasks())
                .thenReturn(List.of(task1, task2));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].description").value("Faire les courses"))
                .andExpect(jsonPath("$[1].description").value("Apprendre Spring Boot"));

//        mockMvc.perform(get("/tasks/hello"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Welcome to the Task Manager API!"));
    }

    @Test
    void shouldReturnEmptyListWhenNoTaskExists() throws Exception {

        Mockito.when(taskService.getTasks())
                .thenReturn(List.of());

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

}
