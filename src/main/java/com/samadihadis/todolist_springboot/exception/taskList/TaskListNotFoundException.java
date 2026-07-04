package com.samadihadis.todolist_springboot.exception.taskList;

public class TaskListNotFoundException extends RuntimeException{
    public TaskListNotFoundException(String message){
        super(message);
    }
}
