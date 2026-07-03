package com.samadihadis.todolist_springboot.service;


import com.samadihadis.todolist_springboot.dto.TaskRequest;
import com.samadihadis.todolist_springboot.dto.TaskResponse;
import com.samadihadis.todolist_springboot.dto.TaskUpdateRequest;
import com.samadihadis.todolist_springboot.entity.Task;
import com.samadihadis.todolist_springboot.entity.TaskList;
import com.samadihadis.todolist_springboot.enums.TaskState;
import com.samadihadis.todolist_springboot.exception.task.TaskNotFoundException;
import com.samadihadis.todolist_springboot.exception.taskList.TaskListNotFoundException;
import com.samadihadis.todolist_springboot.repository.TaskListRepository;
import com.samadihadis.todolist_springboot.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskResponse createTask(Long listId, TaskRequest request) {

        TaskList taskList = taskListRepository.findById(listId)
                .orElseThrow(() -> new TaskListNotFoundException("لیست پیدا نشد"));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .list(taskList)
                .build();

        Task saved = taskRepository.save(task);

        return TaskResponse.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .description(saved.getDescription())
                .state(saved.getTaskState())
                .taskListName(taskList.getName())
                .build();
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(
                        String.format("کار با شناسه %d یافت نشد.", id)
                ));
    }

    public void deleteTask(Long id) {
        getTaskById(id);
        taskRepository.deleteById(id);
    }

    public void updateTask(Long id, TaskUpdateRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(
                        String.format("کار با شناسه %d یافت نشد.", id)
                ));
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        if (request.getState() != null) {
            task.setTaskState(request.getState());
        }
        taskRepository.save(task);
    }

    public List<Task> getTasksByListId(Long listId) {
        taskListRepository.findById(listId)
                .orElseThrow(() -> new TaskListNotFoundException(
                        String.format("لیست با شناسه %d یافت نشد.", listId)
                ));

        return taskRepository.findByListId(listId);
    }

    public List<Task> getTasksByState(TaskState state) {
        return taskRepository.findByTaskState(state);
    }

    public List<Task> getTasksByListAndState(Long listId, TaskState state) {

        taskListRepository.findById(listId)
                .orElseThrow(() -> new TaskListNotFoundException(
                        String.format("لیست با شناسه %d یافت نشد.", listId)
                ));

        return taskRepository.findByListIdAndTaskState(listId, state);
    }
}
