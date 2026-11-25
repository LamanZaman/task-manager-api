package com.example.taskmanagment.dto.request;

import com.example.taskmanagment.entity.enums.TaskPriority;
import com.example.taskmanagment.entity.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Title is required")
    String title;
    @NotEmpty(message = "Description is required")
    String description;
    @NotNull(message = "Status is required")
    @Size(min = 1, max = 20)
    TaskStatus taskStatus;
    @NotNull(message = "Priority is required")
    @Size(min = 1, max = 20)
    TaskPriority taskPriority;
    @Size(min = 1, max = 20)
    Short taskWeight;


}
