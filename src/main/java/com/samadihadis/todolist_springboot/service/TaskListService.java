package com.samadihadis.todolist_springboot.service;


import com.samadihadis.todolist_springboot.dto.TaskListRequest;
import com.samadihadis.todolist_springboot.dto.TaskListResponse;
import com.samadihadis.todolist_springboot.entity.TaskList;
import com.samadihadis.todolist_springboot.repository.TaskListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListResponse createTaskList(TaskListRequest request) {
        TaskList taskList = new TaskList();
        taskList.setName(request.getName());
        taskList.setDescription(request.getDescription());

        TaskList saved = taskListRepository.save(taskList);
        return toResponse(saved);
    }

    public List<TaskListResponse> getAllTaskLists() {
        return taskListRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public TaskListResponse getTaskListById(Long id) {
        return toResponse(findById(id));
    }

    public void deleteTaskList(Long id) {
        findById(id);
        taskListRepository.deleteById(id);
    }

    public TaskListResponse updateTaskList(Long id, TaskListRequest request) {
        TaskList taskList = findById(id);
        taskList.setName(request.getName());
        taskList.setDescription(request.getDescription());
        return toResponse(taskListRepository.save(taskList));
    }

    private TaskList findById(Long id) {
        return taskListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("لیست کارها با شناسه %d یافت نشد.", id)
                ));
    }

    private TaskListResponse toResponse(TaskList taskList) {
        return TaskListResponse.builder()
                .id(taskList.getId())
                .name(taskList.getName())
                .description(taskList.getDescription())
                .createdAt(taskList.getCreatedAt())
                .build();
    }
}
