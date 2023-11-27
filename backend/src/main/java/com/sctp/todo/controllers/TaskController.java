package com.sctp.todo.controllers;

import com.sctp.todo.models.Task;
import com.sctp.todo.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Define this class as a controller for handling Task-related API requests.
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    // Autowire the TaskService to interact with task-related operations.
    @Autowired
    private TaskService taskService;

    // C: Endpoint to create a new task.
    @PostMapping("/")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        return ResponseEntity.ok(taskService.createNewTask(task));
    }

    // R: Endpoint to find a task.
    @GetMapping("/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable Long id) {
        Task task = taskService.findTaskById(id);
        return ResponseEntity.ok(task);
    }

    // R: Endpoint to get a list of all tasks.
    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTask());
    }

    // R: Endpoint to get a list of all completed tasks.
    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks() {
        return ResponseEntity.ok(taskService.findAllCompletedTask());
    }

    // R: Endpoint to get a list of all incomplete tasks.
    @GetMapping("/incomplete")
    public ResponseEntity<List<Task>> getAllIncompleteTasks() {
        return ResponseEntity.ok(taskService.findAllInCompleteTask());
    }

    // U: Endpoint to update an existing task by its id.
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        task.setId(id);
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    // D: Endpoint to delete a task by its id.
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(true);
    }
}