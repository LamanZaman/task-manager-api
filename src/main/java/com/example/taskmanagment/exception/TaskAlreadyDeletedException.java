package com.example.taskmanagment.exception;

public class TaskAlreadyDeletedException extends RuntimeException {
    public TaskAlreadyDeletedException(String message) {
        super(message);
    }
}
