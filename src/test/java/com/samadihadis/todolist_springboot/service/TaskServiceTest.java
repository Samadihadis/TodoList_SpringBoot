package com.samadihadis.todolist_springboot.service;


import com.samadihadis.todolist_springboot.dto.TaskRequest;
import com.samadihadis.todolist_springboot.dto.TaskResponse;
import com.samadihadis.todolist_springboot.entity.Task;
import com.samadihadis.todolist_springboot.entity.TaskList;
import com.samadihadis.todolist_springboot.enums.TaskState;
import com.samadihadis.todolist_springboot.repository.TaskListRepository;
import com.samadihadis.todolist_springboot.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskListRepository taskListRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask_shouldReturnTaskResponse_whenRequestIsValid() {

        Long listId = 1L;

        TaskRequest request = new TaskRequest();
        request.setTitle("Test");
        request.setDescription("Test found");

        TaskList taskList = new TaskList();
        taskList.setId(listId);
        taskList.setName("My List");

        Task savedTask = Task.builder()
                .id(1L)
                .title("Test")
                .description("Test found")
                .taskState(TaskState.PENDING)
                .list(taskList)
                .build();

        when(taskListRepository.findById(listId)).thenReturn(Optional.of(taskList));
        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        TaskResponse taskResponse = taskService.createTask(listId, request);

        assertThat(taskResponse).isNotNull();
        assertThat(taskResponse.getId()).isEqualTo(1L);
        assertThat(taskResponse.getTitle()).isEqualTo("Test");
        assertThat(taskResponse.getDescription()).isEqualTo("Test found");
        assertThat(taskResponse.getState()).isEqualTo(TaskState.PENDING);
        assertThat(taskResponse.getTaskListName()).isEqualTo("My List");

        verify(taskRepository, times(1)).save(any(Task.class));
        verify(taskListRepository, times(1)).findById(listId);
    }

    @Test
    void createTask_shouldThrowException_whenTaskListNotFound() {

        Long listId = 1L;

        TaskRequest request = new TaskRequest();
        request.setTitle("Test");

        when(taskListRepository.findById(listId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.createTask(listId, request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("لیست پیدا نشد");

        verify(taskRepository, never()).save(any());
    }

}

