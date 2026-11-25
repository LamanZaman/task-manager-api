package com.example.taskmanagment.service;

import com.example.taskmanagment.converter.TaskConverter;
import com.example.taskmanagment.dto.request.AddTaskRequestDto;
import com.example.taskmanagment.dto.request.UpdateTaskRequestDto;
import com.example.taskmanagment.dto.response.AllTaskResponseDto;
import com.example.taskmanagment.dto.response.PageResponseDto;
import com.example.taskmanagment.dto.response.TaskResponseDto;
import com.example.taskmanagment.entity.TaskEntity;
import com.example.taskmanagment.entity.enums.TaskPriority;
import com.example.taskmanagment.entity.enums.TaskStatus;
import com.example.taskmanagment.exception.InvalidTaskModificationException;
import com.example.taskmanagment.exception.TaskAlreadyDeletedException;
import com.example.taskmanagment.exception.TaskAlreadyExistsException;
import com.example.taskmanagment.exception.TaskNotFoundException;
import com.example.taskmanagment.repository.TaskRepository;
import com.example.taskmanagment.specification.TaskSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TaskService {

    private final TaskRepository repository;
    private final TaskConverter converter;

    public TaskService(TaskRepository repository, TaskConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public PageResponseDto<AllTaskResponseDto> getAll(
            int pageNumber,
            int pageSize,
            TaskStatus taskStatus,
            TaskPriority taskPriority
    ) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(Sort.Direction.DESC, "id")
        );

        Specification<TaskEntity> specification = Specification.allOf(
                TaskSpecification.isActive(),
                TaskSpecification.hasTaskStatus(taskStatus),
                TaskSpecification.hasTaskPriority(taskPriority)
        );

        Page<TaskEntity> page = repository.findAll(specification, pageable);

        List<AllTaskResponseDto> elements = page.getContent()
                .stream()
                .map(converter::toAllTaskResponseDtoFromEntity)
                .toList();

        return new PageResponseDto<>(
                elements,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext()
        );
    }

    public TaskResponseDto getById(Integer id) {
        TaskEntity taskEntity = getEntityById(id);
        return converter.toTaskResponseDtoFromEntity(taskEntity);
    }

    public void saveTask(AddTaskRequestDto requestDto) {
        log.debug("Task saving, title={}", requestDto.getTitle());

        if (repository.existsByTitleIgnoreCase(requestDto.getTitle())) {
            log.warn("Task already exists, title={}", requestDto.getTitle());
            throw new TaskAlreadyExistsException("Task already exists");
        }

        TaskEntity entity = repository.save(converter.toEntityFromAddTaskRequestDto(requestDto));
        log.info("Task created, id={}", entity.getId());
    }

    public void deleteById(Integer id) {
        log.debug("Task deleting, id={}", id);
        TaskEntity entity = getEntityById(id);

        if (entity.getStatus().equals(false)) {
            log.warn("Task already deleted, id={}", id);
            throw new TaskAlreadyDeletedException("Task already deleted");
        }

        entity.setStatus(false);
        repository.save(entity);
        log.info("Task deleted, id={}", id);
    }

    public void updateTask(Integer id, UpdateTaskRequestDto requestDto) {
        log.debug("Task updating, id={}", id);
        TaskEntity entity = getEntityById(id);

        if (repository.existsByTitleIgnoreCaseAndIdNot(requestDto.getTitle(), id)) {
            log.warn("Task already exists, id={}, title={}", id, requestDto.getTitle());
            throw new TaskAlreadyExistsException("Task already exists");
        }

        if (entity.getStatus().equals(false) || entity.getTaskStatus().equals(TaskStatus.DONE)) {
            log.warn("Task modification failed, id={}, status={}, taskStatus={}",
                    id, entity.getStatus(), entity.getTaskStatus());
            throw new InvalidTaskModificationException("Invalid task modification");
        }

        entity.setTitle(requestDto.getTitle().trim());
        entity.setDescription(requestDto.getDescription().trim());
        entity.setTaskStatus(requestDto.getTaskStatus());
        entity.setTaskPriority(requestDto.getTaskPriority());
        entity.setTaskWeight(requestDto.getTaskWeight());

        repository.save(entity);
        log.info("Task updated, id={}", id);
    }

    private TaskEntity getEntityById(Integer id) {
        return repository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> {
                    log.warn("Task not found, id={}", id);
                    return new TaskNotFoundException("Task not found");
                });
    }
}