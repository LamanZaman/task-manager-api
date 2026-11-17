package com.example.taskmanagment.converter;

import com.example.taskmanagment.dto.request.AddTaskRequestDto;
import com.example.taskmanagment.dto.request.UpdateTaskRequestDto;
import com.example.taskmanagment.dto.response.AllTaskResponseDto;
import com.example.taskmanagment.dto.response.TaskResponseDto;
import com.example.taskmanagment.entity.TaskEntity;
import com.example.taskmanagment.entity.enums.TaskStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskConverter {

    public TaskResponseDto toTaskResponseDtoFromEntity(TaskEntity taskEntity) {
        return new TaskResponseDto(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getTaskStatus(),
                taskEntity.getTaskPriority(),
                taskEntity.getTaskWeight(),
                taskEntity.getCreatedAt()

        );
    }

    public AllTaskResponseDto toAllTaskResponseDtoFromEntity(TaskEntity taskEntity) {
        return new AllTaskResponseDto(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getTaskStatus()
        );
    }

    public TaskEntity toEntityFromAddTaskRequestDto(AddTaskRequestDto requestDto) {
        TaskEntity entity = new TaskEntity();
        entity.setTitle(requestDto.getTitle());
        entity.setDescription(requestDto.getDescription());
        entity.setTaskStatus(TaskStatus.TODO);
        entity.setTaskPriority(requestDto.getTaskPriority());
        entity.setTaskWeight(requestDto.getTaskWeight());
        entity.setStatus(true);
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }


}
