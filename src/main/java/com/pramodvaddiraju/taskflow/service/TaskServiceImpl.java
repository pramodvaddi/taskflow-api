package com.pramodvaddiraju.taskflow.service;

import com.pramodvaddiraju.taskflow.dto.TaskRequest;
import com.pramodvaddiraju.taskflow.dto.TaskResponse;
import com.pramodvaddiraju.taskflow.entity.Task;
import com.pramodvaddiraju.taskflow.exception.ResourceNotFoundException;
import com.pramodvaddiraju.taskflow.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
    private ModelMapper modelMapper;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = modelMapper.map(taskRequest, Task.class);
        Task created = taskRepository.save(task);
        log.info("Task created");
        return modelMapper.map(created, TaskResponse.class);
    }

    @Override
    public Page<TaskResponse> getAllTasks(Pageable pageable) {
        Page<Task> tasks = taskRepository.findAll(pageable);

        return tasks.map(t -> modelMapper.map(tasks, TaskResponse.class));
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Task not found with id: "+ id)
        );
        log.info("Task fetched with id: {}", task.getId());

        return modelMapper.map(task, TaskResponse.class);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Task existingTask = taskRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Task not found with id: " + id)
        );
        existingTask.setTitle(existingTask.getTitle());
        existingTask.setDescription(existingTask.getDescription());
        existingTask.setPriority(existingTask.getPriority());
        existingTask.setCompleted(existingTask.isCompleted());
        Task updatedTask = taskRepository.save(existingTask);
        log.info("Task updated with id: {}", updatedTask.getId());
        return modelMapper.map(updatedTask, TaskResponse.class);
    }

    @Override
    public void deleteTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Task not found with id: " + id)
        );
        taskRepository.delete(task);

    }

    @Override
    public List<TaskResponse> getTasksByCompleted(boolean completed) {

        List<Task> tasks = taskRepository.findByCompleted(completed);

        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponse> getTasksByPriority(String priority) {
        List<Task> tasks = taskRepository.findByPriority(priority);

        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskResponse.class))
                .collect(Collectors.toList());
    }
}
