package com.samadihadis.todolist_springboot.repository;

import com.samadihadis.todolist_springboot.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList , Long> {
}
