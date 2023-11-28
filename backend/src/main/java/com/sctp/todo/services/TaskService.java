package com.sctp.todo.services;

import com.sctp.todo.exceptions.TaskNotFoundException;
import com.sctp.todo.models.Task;
import com.sctp.todo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Define this class as a service for managing Task-related operations.
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // C: Method to create and save a new task.
    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    // R: Method to find a task by its id.
    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // R: Method to retrieve a list of all tasks.
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    // R: Method to retrieve a list of all completed tasks.
    public List<Task> findAllCompletedTask() {
        return taskRepository.findByCompletedTrue();
    }

    // R: Method to retrieve a list of all incomplete tasks.
    public List<Task> findAllInCompleteTask() {
        return taskRepository.findByCompletedFalse();
    }

    // U: Method to update an existing task.
    public Task updateTask(Task task) {
        return taskRepository.findById(task.getId()).map(existingTask -> {
            existingTask.setTask(task.getTask());
            existingTask.setCompleted(task.isCompleted());
            return taskRepository.save(existingTask);
        }).orElseThrow(() -> new TaskNotFoundException("Task with id " + task.getId() + " not found"));
    }

    // D: Method to delete a task.
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
        taskRepository.delete(task);
    }
}
