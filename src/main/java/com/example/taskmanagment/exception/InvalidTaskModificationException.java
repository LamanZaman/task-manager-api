package com.example.taskmanagment.exception;

public class InvalidTaskModificationException extends RuntimeException {
    public InvalidTaskModificationException(String message) {
        super(message);
    }
}
