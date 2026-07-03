package com.samadihadis.todolist_springboot.controller;


import com.samadihadis.todolist_springboot.dto.TaskRequest;
import com.samadihadis.todolist_springboot.dto.TaskResponse;
import com.samadihadis.todolist_springboot.dto.TaskUpdateRequest;
import com.samadihadis.todolist_springboot.entity.Task;
import com.samadihadis.todolist_springboot.enums.TaskState;
import com.samadihadis.todolist_springboot.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/{listId}")
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest taskRequest,
            @PathVariable Long listId
    ) {
        TaskResponse response = taskService.createTask(listId, taskRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(
                String.format("کار با شناسه %d از لیست کارها حذف شد.", id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(
            @Valid @RequestBody TaskUpdateRequest request,
            @PathVariable Long id
    ) {
        taskService.updateTask(id, request);
        return ResponseEntity.ok(
                String.format("کار با شناسه %d بروزرسانی شد.", id)
        );
    }

    @GetMapping("/list/{listId}")
    public ResponseEntity<List<Task>> getTasksByList(@PathVariable Long listId) {
        return ResponseEntity.ok(taskService.getTasksByListId(listId));
    }

    @GetMapping("/state/{taskState}")
    public ResponseEntity<List<Task>> getTasksByState(@PathVariable TaskState taskState) {
        return ResponseEntity.ok(taskService.getTasksByState(taskState));
    }

    @GetMapping("/list/{listId}/state/{state}")
    public ResponseEntity<List<Task>> getTasksByListAndState(
            @PathVariable Long listId,
            @PathVariable TaskState state
    ) {
        return ResponseEntity.ok(taskService.getTasksByListAndState(listId, state));
    }
}
