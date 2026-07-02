package com.samadihadis.todolist_springboot.repository;

import com.samadihadis.todolist_springboot.entity.Task;
import com.samadihadis.todolist_springboot.enums.TaskState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByListId(Long listId);
    List<Task> findByTaskState(TaskState taskState);
    List<Task> findByListIdAndTaskState(Long listId, TaskState taskState);
}
