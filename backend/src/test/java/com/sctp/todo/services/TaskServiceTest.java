package com.sctp.todo.services;

import com.sctp.todo.models.Task;
import com.sctp.todo.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task1, task2, task3;

    @BeforeEach
    void init() {
        task1 = new Task("Task 1", false);
        task2 = new Task("Task 2", true);
        task3 = new Task("Task 3", false);
        task1.setId(1L);
        task2.setId(2L);
        task3.setId(3L);
    }

    @Test
    void createNewTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task1);
        Task newTask = taskService.createNewTask(task1);

        assertNotNull(newTask);
        assertThat(newTask.getId()).isEqualTo(1L);
    }

    @Test
    void findTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
        Optional<Task> foundTask = taskService.findTaskById(1L);

        assertTrue(foundTask.isPresent());
        assertEquals("Task 1", foundTask.get().getTask());
    }

    @Test
    void getAllTask() {
        List<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task2);

        when(taskRepository.findAll()).thenReturn(list);
        List<Task> results = taskService.getAllTask();

        assertNotNull(results);
        assertEquals(2, results.size());
    }

//    @Test
//    void updateTask() {
//        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
//        task1.setTask("Updated Task 1");
//        task1.setCompleted(true);
//        Task updatedTask = taskService.updateTask(task1);
//
//        assertEquals("Updated Task 1", updatedTask.getTask());
//        assertTrue(updatedTask.isCompleted());
//    }

    @Test
    void deleteTask() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
        doNothing().when(taskRepository).delete(any(Task.class));
        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).delete(task1);
    }
}
