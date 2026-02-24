package com.pramodvaddiraju.taskflow.controller;

import com.pramodvaddiraju.taskflow.dto.TaskRequest;
import com.pramodvaddiraju.taskflow.dto.TaskResponse;
import com.pramodvaddiraju.taskflow.entity.Task;
import com.pramodvaddiraju.taskflow.response.ApiResponse;
import com.pramodvaddiraju.taskflow.service.TaskService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    ResponseEntity<ApiResponse<TaskResponse>> createTask(@Valid @RequestBody TaskRequest taskRequest){
        TaskResponse response = taskService.createTask(taskRequest);
        ApiResponse<TaskResponse> apiResponse = new ApiResponse<>(true, "Created",response);
        log.info("Task created with id: {}", response.getId());
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping
    ResponseEntity<ApiResponse<Page<TaskResponse>>> getAllTasks(Pageable pageable){
        Page<TaskResponse> response = taskService.getAllTasks(pageable);
        ApiResponse<Page<TaskResponse>> apiResponse = new ApiResponse<>(true, "Fetched all", response);
        log.info("All tasks fetched successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<TaskResponse>> getTaskById(@PathVariable Long id){
        TaskResponse response = taskService.getTaskById(id);
        ApiResponse<TaskResponse> apiResponse = new ApiResponse<>(true, "Fetched by id", response);
        log.info("Task fetched by id: {}", response.getId());
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<TaskResponse>> updateTask(@Valid @PathVariable Long id, @RequestBody TaskRequest taskRequest){
        TaskResponse response = taskService.updateTask(id, taskRequest);
        ApiResponse<TaskResponse> apiResponse = new ApiResponse<>(true, "Updated Task", response);
        log.info("Task Updated successfully with id: {}", response.getId());
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id){
        taskService.deleteTaskById(id);
        ApiResponse<Void> apiResponse = new ApiResponse<>(true, "Deleted", null);
        log.info("Task deleted by id: {}", id);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/completed")
    ResponseEntity<ApiResponse<List<TaskResponse>>> getTasksByCompleted(@RequestParam boolean completed){
        List<TaskResponse> tasks = taskService.getTasksByCompleted(completed);
        ApiResponse<List<TaskResponse>> apiResponse = new ApiResponse<>(true, "Tasks filtered by status", tasks);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/priority")
    ResponseEntity<ApiResponse<List<TaskResponse>>> getTasksByPriority(@RequestParam String priority){
        List<TaskResponse> tasks = taskService.getTasksByPriority(priority);
        ApiResponse<List<TaskResponse>> apiResponse = new ApiResponse<>(true, "Tasks filtered by priority", tasks);
        return ResponseEntity.ok(apiResponse);
    }


}
