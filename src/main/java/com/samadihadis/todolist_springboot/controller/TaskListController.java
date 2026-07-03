package com.samadihadis.todolist_springboot.controller;


import com.samadihadis.todolist_springboot.dto.TaskListRequest;
import com.samadihadis.todolist_springboot.dto.TaskListResponse;
import com.samadihadis.todolist_springboot.service.TaskListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lists")
public class TaskListController {

    private final TaskListService taskListService;

    @PostMapping
    public ResponseEntity<TaskListResponse> createTaskList(
            @Valid @RequestBody TaskListRequest request
    ) {
        return ResponseEntity.ok(taskListService.createTaskList(request));
    }

    @GetMapping
    public ResponseEntity<List<TaskListResponse>> getAllTaskLists() {
        return ResponseEntity.ok(taskListService.getAllTaskLists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskListResponse> getTaskListById(@PathVariable Long id) {
        return ResponseEntity.ok(taskListService.getTaskListById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskList(@PathVariable Long id) {
        taskListService.deleteTaskList(id);
        return ResponseEntity.ok(
                String.format("لیست کار با شناسه %d حذف شد.", id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskListResponse> updateTaskList(
            @Valid @RequestBody TaskListRequest request,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(taskListService.updateTaskList(id, request));
    }
}
