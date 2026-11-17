package com.example.taskmanagment.dto.response;

import com.example.taskmanagment.entity.enums.TaskPriority;
import com.example.taskmanagment.entity.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
@AllArgsConstructor
public class TaskResponseDto {

    private Integer id;
    String title;

    String description;

    TaskStatus taskStatus = TaskStatus.TODO;

    TaskPriority taskPriority;

    String taskWeight;

    LocalDateTime createdAt = LocalDateTime.now();
}
