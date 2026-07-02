package com.samadihadis.todolist_springboot.controller;


import com.samadihadis.todolist_springboot.entity.TaskList;
import com.samadihadis.todolist_springboot.service.TaskListService;
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
    public ResponseEntity<TaskList> createTaskList(@RequestBody TaskList taskList) {
        TaskList savedTaskList = taskListService.createTaskList(taskList);
        return ResponseEntity.ok(savedTaskList);
    }

    @GetMapping
    public ResponseEntity<List<TaskList>> getAllTaskLists() {
        return ResponseEntity.ok(taskListService.getAllTaskLists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskList> getTaskListById(@PathVariable Long id) {
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
    public ResponseEntity<?> updateTaskList(@RequestBody TaskList taskList, @PathVariable Long id) {
        taskListService.updateTaskList(id , taskList);
        return ResponseEntity.ok(
                String.format("لیست کارها با شناسه %d بروزرسانی شد.", id)
        );
    }
}
