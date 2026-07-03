package com.samadihadis.todolist_springboot.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.samadihadis.todolist_springboot.enums.TaskState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskState taskState;

    @ManyToOne
    @JoinColumn(name = "list_id" , nullable = false)
    @JsonBackReference
    private TaskList list;

    @PrePersist
    public void prePersistTaskState() {
        this.taskState = TaskState.PENDING;
    }
}
