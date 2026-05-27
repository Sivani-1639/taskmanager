package com.example.taskmanager.dto;

import com.example.taskmanager.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

public class TaskDtos {
    public record TaskRequest(
            @NotBlank String title,
            String description,
            Instant dueDate,
            TaskStatus status
    ) {}
    public record TaskUpdateRequest(
            String title,
            String description,
            Instant dueDate,
            TaskStatus status
    ) {}
}
