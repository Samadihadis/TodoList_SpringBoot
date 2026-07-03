package com.samadihadis.todolist_springboot.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskListResponse {

    private Long id;
    private String name;
    private String description;
    private LocalDate createdAt;
}
