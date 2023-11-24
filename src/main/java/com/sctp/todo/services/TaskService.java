package com.sctp.todo.services;

import com.sctp.todo.models.Task;
import com.sctp.todo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Define this class as a service for managing Task-related operations.
@Service
public class TaskService {
    // Autowire the TaskRepository to interact with the database.
    @Autowired
    private TaskRepository taskRepository;

    // Method to create and save a new task.
    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    // Method to retrieve a list of all tasks.
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    // Method to find a task by its id.
    public Task findTaskById(Long id) {
        return taskRepository.getById(id);
    }

    // Method to retrieve a list of all completed tasks.
    public List<Task> findAllCompletedTask() {
        return taskRepository.findByCompletedTrue();
    }

    // Method to retrieve a list of all incomplete tasks.
    public List<Task> findAllInCompleteTask() {
        return taskRepository.findByCompletedFalse();
    }

    // Method to delete a task.
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Method to update an existing task.
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
}
