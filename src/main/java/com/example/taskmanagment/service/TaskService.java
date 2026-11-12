package com.example.taskmanagment.service;

import com.example.taskmanagment.entity.TaskEntity;
import com.example.taskmanagment.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    //dependency injection CTOR injection ile
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }


    public List<TaskEntity> getAll() {
        return repository.findAll();
    }

    public TaskEntity getById(Integer id) {
        return repository.findById(id)
                .get();//nullpointer exception ala bilirik bele yazdqda
    }

    public void saveTask(TaskEntity taskEntity) {
        repository.save(taskEntity);
    }


    public void deleteTask(Integer id) {
        if(!repository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }
        repository.deleteById(id);
    }

  
}
