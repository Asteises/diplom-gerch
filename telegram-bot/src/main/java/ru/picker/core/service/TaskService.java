package ru.picker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Task;
import ru.picker.core.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task getTaskByName(String taskName) {
        return taskRepository.findTaskByName(taskName)
            .orElseThrow(() -> new RuntimeException("Task " + taskName + "not found"));
    }

}
