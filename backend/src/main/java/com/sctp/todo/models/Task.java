package com.sctp.todo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

// Define a class called Task as an entity for database persistence.
@Entity
public class Task {
    // Define id as the primary key, which will be auto-generated.
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Define a field to store the task description.
    @Column(name="task")
    @NotBlank(message = "Task cannot be blank.")
    private String task;

    // Define a field to represent whether the task is completed or not.
    private boolean completed;

    // Empty constructor.
    public Task() {
    }

    // Constructor to initialize a Task object with task description and completion status.
    public Task(String task, boolean completed) {
        this.task = task;
        this.completed = completed;
    }

    // Getter method to retrieve the id of the Task.
    public Long getId() {
        return id;
    }

    // Setter method to set the id of the Task.
    public void setId(Long id) {
        this.id = id;
    }

    // Getter method to retrieve the task description.
    public String getTask() {
        return task;
    }

    // Setter method to set the task description.
    public void setTask(String task) {
        this.task = task;
    }

    // Getter method to check if the task is completed or not.
    public boolean isCompleted() {
        return completed;
    }

    // Setter method to mark the task as completed or not.
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}