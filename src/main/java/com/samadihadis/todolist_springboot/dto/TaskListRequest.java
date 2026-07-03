package com.samadihadis.todolist_springboot.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskListRequest {

    @NotBlank(message = "نام لیست نمی‌تواند خالی باشد")
    private String name;

    private String description;
}
