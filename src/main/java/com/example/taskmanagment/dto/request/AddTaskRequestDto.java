package com.example.taskmanagment.dto.request;

import com.example.taskmanagment.entity.enums.TaskPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
@AllArgsConstructor
public class AddTaskRequestDto {

    private String title;

    private String description;

    private TaskPriority taskPriority;

    private String taskWeight;




}
