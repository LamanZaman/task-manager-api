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
@Entity
@ToString(exclude = {"createdBy","assignee"})
@Table(name = "tasks")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TaskEntity {
    @Id//primary key kimi qebul eden anotasiya
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment ucun
    Integer id;

    @Column(name = "title", length = 30, nullable = false, unique = true)
    String title;

    @Column(name = "description", columnDefinition = "TEXT", length = 2000)
    String description;

    @Column(name = "task_status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    TaskStatus taskStatus = TaskStatus.TODO;

    @Column(name = "task_priority", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    TaskPriority taskPriority;

    @Column(name = "task_weight")
    Short taskWeight;

    @Column(name = "status", nullable = false)
    Boolean status = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    UserEntity createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    UserEntity assignee;



    @PrePersist
    public void onCreate() {
        if (taskWeight == null) {
            taskStatus = TaskStatus.TODO;
        }
        if (status == null) {
            status = true;
        }
        createdAt = LocalDateTime.now();
    }
}