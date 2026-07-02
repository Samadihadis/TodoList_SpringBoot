package com.samadihadis.todolist_springboot.service;


import com.samadihadis.todolist_springboot.entity.TaskList;
import com.samadihadis.todolist_springboot.repository.TaskListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskList createTaskList(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    public List<TaskList> getAllTaskLists() {
        return taskListRepository.findAll();
    }

    public TaskList getTaskListById(Long id) {
        return taskListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("لیست کارها به شناسه %d یافت نشد.", id)
                ));
    }

    public void deleteTaskList(Long id) {
        getTaskListById(id);
        taskListRepository.deleteById(id);
    }

}
