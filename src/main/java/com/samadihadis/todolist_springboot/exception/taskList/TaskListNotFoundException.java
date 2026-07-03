package com.samadihadis.todolist_springboot.exception.taskList;

import com.samadihadis.todolist_springboot.exception.task.TaskNotFoundException;

public class TaskListNotFoundException extends RuntimeException{
    public TaskListNotFoundException(String message){
        super(message);
    }
}
