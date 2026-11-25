package com.example.taskmanagment.controller;

import com.example.taskmanagment.dto.request.AddTaskRequestDto;
import com.example.taskmanagment.dto.request.UpdateTaskRequestDto;
import com.example.taskmanagment.dto.response.AllTaskResponseDto;
import com.example.taskmanagment.dto.response.PageResponseDto;
import com.example.taskmanagment.dto.response.TaskResponseDto;
import com.example.taskmanagment.entity.enums.TaskPriority;
import com.example.taskmanagment.entity.enums.TaskStatus;
import com.example.taskmanagment.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public PageResponseDto<AllTaskResponseDto> getAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) TaskStatus taskStatus,
            @RequestParam(required = false) TaskPriority taskPriority
    ) {
        return service.getAll(pageNumber, pageSize, taskStatus, taskPriority);
    }

    @GetMapping("/{id}")
    public TaskResponseDto getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTask(@RequestBody @Valid AddTaskRequestDto requestDto) {
        service.saveTask(requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable Integer id,
                           @RequestBody @Valid UpdateTaskRequestDto requestDto) {
        service.updateTask(id, requestDto);
    }
}