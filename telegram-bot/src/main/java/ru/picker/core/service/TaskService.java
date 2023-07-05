package ru.picker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Task;
import ru.picker.core.repository.TaskRepository;

import javax.ws.rs.NotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task findById(UUID id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Task with ID: %s not found", id)));
    }

}

