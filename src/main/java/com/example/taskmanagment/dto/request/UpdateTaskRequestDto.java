package com.example.taskmanagment.dto.request;

import com.example.taskmanagment.entity.enums.TaskPriority;
import com.example.taskmanagment.entity.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTaskRequestDto {

    String title;
    String description;
    TaskStatus taskStatus;
    TaskPriority taskPriority;
    String taskWeight;


}
