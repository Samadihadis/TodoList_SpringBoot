package com.samadihadis.todolist_springboot.dto;

import com.samadihadis.todolist_springboot.enums.TaskState;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskUpdateRequest {

    @NotBlank(message = "عنوان نمی‌تواند خالی باشد")
    private String title;

    private String description;

    private TaskState state;
}
