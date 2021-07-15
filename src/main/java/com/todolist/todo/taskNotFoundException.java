package com.todolist.todo;

public class taskNotFoundException extends Throwable {
    public taskNotFoundException(String message) {
        super(message);
    }
}
