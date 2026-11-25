package com.example.taskmanagment.dto.request;

import com.example.taskmanagment.entity.enums.TaskPriority;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
@AllArgsConstructor
public class AddTaskRequestDto {
    @NotNull(message = "Title is required")
    private String title;
    @NotEmpty(message = "Description is required")
    private String description;
    @Size(min=1,max = 20)
    @NotNull(message = "Priority status is required")
    private TaskPriority taskPriority;
    @Size(min = 1, max = 20)
    private Short taskWeight;


}
