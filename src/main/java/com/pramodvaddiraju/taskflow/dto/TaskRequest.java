package com.pramodvaddiraju.taskflow.dto;

import jakarta.validation.constraints.NotBlank;

public class TaskRequest {

    @NotBlank(message = "title cannot be blank")
    private String title;
    @NotBlank(message = "description cannot be blank")
    private String description;
    @NotBlank(message = "priority cannot be blank")
    private String priority;
    private boolean completed;
    private String email;

    public TaskRequest(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
