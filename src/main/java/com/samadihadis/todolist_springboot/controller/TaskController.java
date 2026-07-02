package com.samadihadis.todolist_springboot.controller;


import com.samadihadis.todolist_springboot.entity.Task;
import com.samadihadis.todolist_springboot.enums.TaskState;
import com.samadihadis.todolist_springboot.service.TaskService;
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
    public ResponseEntity<?> createTask(@RequestBody Task task, @PathVariable Long listId) {
        Task savedTask = taskService.createTask(listId, task);
        return ResponseEntity.ok(savedTask);
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
    public ResponseEntity<?> updateTask(@RequestBody Task task,@PathVariable Long id) {
        taskService.updateTask(id , task);
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
