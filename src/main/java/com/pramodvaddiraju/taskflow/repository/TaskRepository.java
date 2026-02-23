package com.pramodvaddiraju.taskflow.repository;

import com.pramodvaddiraju.taskflow.service.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    void findByCompleted(boolean completed);
    void findByPriority(String priority);
}
