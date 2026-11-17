package com.example.taskmanagment.dto.response;

import com.example.taskmanagment.entity.enums.TaskPriority;
import com.example.taskmanagment.entity.enums.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
@AllArgsConstructor
public class AllTaskResponseDto {

    private Integer id;

    private String title;

    private TaskStatus taskStatus = TaskStatus.TODO;




}
