package com.samadihadis.todolist_springboot.entity;


import com.samadihadis.todolist_springboot.enums.TaskState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskState taskState = TaskState.PENDING;

    @ManyToOne
    @JoinColumn(name = "list_id" , nullable = false)
    private TaskList list;
}
