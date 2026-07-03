package com.samadihadis.todolist_springboot.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequest {

    @NotBlank(message = "عنوان نمی‌تواند خالی باشد")
    private String title;

    private String description;
}
