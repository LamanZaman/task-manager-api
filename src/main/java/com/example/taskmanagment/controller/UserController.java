package com.example.taskmanagment.controller;

import com.example.taskmanagment.dto.request.AddUserRequestDto;
import com.example.taskmanagment.dto.request.UpdateUserRequestDto;
import com.example.taskmanagment.dto.response.AllResponseUserDto;
import com.example.taskmanagment.dto.response.AllTaskResponseDto;
import com.example.taskmanagment.dto.response.PageResponseDto;
import com.example.taskmanagment.dto.response.UserResponseDto;
import com.example.taskmanagment.entity.enums.TaskPriority;
import com.example.taskmanagment.entity.enums.TaskStatus;
import com.example.taskmanagment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public PageResponseDto<AllResponseUserDto> getAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize

    ) {
        return userService.getAll(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(Integer id) {
        return userService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(AddUserRequestDto requestDto) {
        userService.saveUser(requestDto);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Integer id) {
        userService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody @Valid UpdateUserRequestDto requestDto) {
        userService.updateUser(id, requestDto);
    }


}
