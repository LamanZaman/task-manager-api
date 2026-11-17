package com.example.taskmanagment.service;

import com.example.taskmanagment.converter.TaskConverter;
import com.example.taskmanagment.dto.request.AddTaskRequestDto;
import com.example.taskmanagment.dto.request.UpdateTaskRequestDto;
import com.example.taskmanagment.dto.response.AllTaskResponseDto;
import com.example.taskmanagment.dto.response.TaskResponseDto;
import com.example.taskmanagment.entity.TaskEntity;
import com.example.taskmanagment.entity.enums.TaskStatus;
import com.example.taskmanagment.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {


    //dependency injection CTOR injection ile
    private final TaskRepository repository;
    private final TaskConverter converter;

    public TaskService(TaskRepository repository, TaskConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    public List<AllTaskResponseDto> getAll() {
        return repository.findAll().stream()
                .map(converter::toAllTaskResponseDtoFromEntity)
                .collect(Collectors.toList());

    }


    public TaskResponseDto getById(Integer id) {
        TaskEntity taskEntity = getEntityById(id);
        return converter.toTaskResponseDtoFromEntity(taskEntity);
    }

    public void saveTask(AddTaskRequestDto requestDto) {

        repository.save(converter.toEntityFromAddTaskRequestDto(requestDto));
    }


    public void deleteTask(Integer id) {
        TaskEntity entity = getEntityById(id);
        entity.setStatus(false);
        repository.save(entity);
    }

    public void updateTask(Integer id, UpdateTaskRequestDto requestDto) {
        TaskEntity entity = new TaskEntity();
        entity.setTitle(requestDto.getTitle());
        entity.setDescription(requestDto.getDescription());
        entity.setTaskStatus(requestDto.getTaskStatus());
        entity.setTaskPriority(requestDto.getTaskPriority());
        entity.setTaskWeight(requestDto.getTaskWeight());
        repository.save(entity);
    }

    private TaskEntity getEntityById(Integer id) {

        return repository.findById(id).get();
    }

}
