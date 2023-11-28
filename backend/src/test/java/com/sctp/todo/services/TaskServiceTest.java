package com.sctp.todo.services;

import com.sctp.todo.exceptions.TaskNotFoundException;
import com.sctp.todo.models.Task;
import com.sctp.todo.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task10, task20, task30;

    @BeforeEach
    public void init() {
        task10 = new Task("10", true);
        task20 = new Task("20", true);
        task30 = new Task("30", false);
    }

    @Test
    public void testCreateNewTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task10);
        Task newTask = taskService.createNewTask(task10);

        assertNotNull(newTask);
        assertThat(newTask.getId()).isEqualTo("10");
    }

    @Test
    public void testFindTaskById() {
        List<Task> list = new ArrayList<>();
        list.add(task10);
        list.add(task20);

        when(taskRepository.findAll()).thenReturn(list);
        List<Task> results = (List<Task>) taskService.findTaskById();

        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Test
    public void testFindTaskByIdNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.findTaskById(1L));

        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void deleteProduct() {
        String productId = "1";
        when(taskRepository.findById(anyString())).thenReturn(Optional.of(task10));
        doNothing().when(taskRepository).deleteById(anyString());
        Optional<Task> deletedProduct = taskService.deleteTask(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);

    }

}
