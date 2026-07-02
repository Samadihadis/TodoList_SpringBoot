package com.samadihadis.todolist_springboot.service;


import com.samadihadis.todolist_springboot.entity.Task;
import com.samadihadis.todolist_springboot.entity.TaskList;
import com.samadihadis.todolist_springboot.enums.TaskState;
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

    public Task createTask(Long listId, Task task) {

        TaskList taskList = taskListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("لیست با شناسه %d یافت نشد.", listId)
                ));
        if (task.getTaskState() == null) {
            task.setTaskState(TaskState.PENDING);
        }
        task.setList(taskList);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("کار با شناسه %d یافت نشد.", id)
                ));
    }

    public void deleteTask(Long id) {
        getTaskById(id);
        taskRepository.deleteById(id);
    }

    public void updateTask(Long id, Task task) {
        Task newTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("کار با شناسه %d یافت نشد.", id)
                ));
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        taskRepository.save(newTask);
    }
}
