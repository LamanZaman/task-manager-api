package com.example.taskmanagment.controller;

import com.example.taskmanagment.dto.request.AddTaskRequestDto;
import com.example.taskmanagment.dto.request.UpdateTaskRequestDto;
import com.example.taskmanagment.dto.response.AllTaskResponseDto;
import com.example.taskmanagment.dto.response.TaskResponseDto;
import com.example.taskmanagment.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;

    }

    @GetMapping("/all")
    public List<AllTaskResponseDto> getAll() {
        return taskService.getAll();

    }

    @GetMapping("/{id}")
    public TaskResponseDto getById(@PathVariable Integer id) {
        return taskService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public void saveTask(@RequestBody AddTaskRequestDto requestDto) {
        taskService.saveTask(requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/update/{id}")
    public void updateTask(@PathVariable Integer id,@RequestBody UpdateTaskRequestDto requestDto) {
        taskService.updateTask(id, requestDto);
    }

}
