package com.pramodvaddiraju.taskflow.service;

import com.pramodvaddiraju.taskflow.dto.TaskRequest;
import com.pramodvaddiraju.taskflow.dto.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskRequest taskRequest);
    Page<TaskResponse> getAllTasks(Pageable pageable);
    TaskResponse getTaskById(Long id);
    TaskResponse updateTask(Long id, TaskRequest taskRequest);
    void deleteTaskById(Long id);
    List<TaskResponse> getTasksByCompleted(boolean completed);
    List<TaskResponse> getTasksByPriority(String priority);
}
