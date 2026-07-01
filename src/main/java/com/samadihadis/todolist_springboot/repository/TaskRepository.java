package com.samadihadis.todolist_springboot.repository;

import com.samadihadis.todolist_springboot.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
