package com.samadihadis.todolist_springboot.dto;

import com.samadihadis.todolist_springboot.enums.TaskState;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private TaskState state;
    private String taskListName;
}
