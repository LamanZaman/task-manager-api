package com.example.taskmanagment.entity;

import com.example.taskmanagment.entity.enums.TaskPriority;
import com.example.taskmanagment.entity.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskEntity {
    @Id//primary key kimi qebul eden anotasiya
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment ucun
    private Integer id;

    @Column(name = "title", length = 30)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus = TaskStatus.TODO;

    @Column(name = "task_priority")
    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriority;

    @Column(name = "task_weight")
    private String taskWeight;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}