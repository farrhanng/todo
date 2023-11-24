package com.sctp.todo.repositories;

import com.sctp.todo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Define this interface as a repository for Task entities.
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Define a method to find a task by its task description.
    public Task findByTask(String task);

    // Define a method to find all tasks that are marked as completed.
    public List<Task> findByCompletedTrue();

    // Define a method to find all tasks that are not marked as completed.
    public List<Task> findByCompletedFalse();

    // Define a method to retrieve all tasks.
    public List<Task> findAll();

    // Define a method to retrieve a task by its id.
    public Task getById(Long id);
}
