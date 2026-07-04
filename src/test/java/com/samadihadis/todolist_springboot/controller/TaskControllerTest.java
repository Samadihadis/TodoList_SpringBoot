package com.samadihadis.todolist_springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samadihadis.todolist_springboot.dto.TaskRequest;
import com.samadihadis.todolist_springboot.dto.TaskResponse;
import com.samadihadis.todolist_springboot.enums.TaskState;
import com.samadihadis.todolist_springboot.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTask_shouldReturn200_whenRequestIsValid() throws Exception {

        TaskRequest request = new TaskRequest();
        request.setTitle("Test");
        request.setDescription("Test Desc");

        TaskResponse response = new TaskResponse(
                1L,
                "Test",
                "Test Desc",
                TaskState.PENDING,
                "My List"
        );

        when(taskService.createTask(eq(1L), any(TaskRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test"))
                .andExpect(jsonPath("$.description").value("Test Desc"));

        verify(taskService, times(1))
                .createTask(eq(1L), any(TaskRequest.class));
    }
}

