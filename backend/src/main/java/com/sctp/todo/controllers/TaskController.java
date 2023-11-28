package com.sctp.todo.controllers;

import com.sctp.todo.models.Task;
import com.sctp.todo.services.TaskService;
import com.sctp.todo.exceptions.TaskNotFoundException;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // C: Endpoint to create a new task.
    @PostMapping("/")
    public ResponseEntity<Object> createTask(@Valid @RequestBody Task task) {
        return new ResponseEntity<>(taskService.createNewTask(task), HttpStatus.BAD_REQUEST);
    }

    // R: Endpoint to find a task.
    @GetMapping("/{id}")
    public ResponseEntity<Object> findTaskById(@PathVariable("id") Long id) {
        Task task = taskService.findTaskById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found."));

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
    public ResponseEntity<Object> updateTask(@PathVariable("id") Long id, @Valid @RequestBody Task task) {
        task.setId(id);

        Task updatedTask = taskService.updateTask(task);

        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            throw new TaskNotFoundException("Task with id " + id + " not found.");
        }
    }

    // D: Endpoint to delete a task by its id.
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>("Task is deleted successfully.", HttpStatus.OK);
    }
}