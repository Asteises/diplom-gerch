package ru.picker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Task;
import ru.picker.core.mapper.TaskMapper;
import ru.picker.core.model.IncomeTaskDto;
import ru.picker.core.model.TaskDto;
import ru.picker.core.repository.TaskRepository;

import javax.ws.rs.NotFoundException;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskDto add(IncomeTaskDto incomeTaskDto) {
        Task task = TaskMapper.INSTANCE.map(
                incomeTaskDto,
                this);
        taskRepository.save(task);
        return TaskMapper.INSTANCE.map(task);
    }

    public TaskDto get(String id) {
        Task task = findById(id);
        return TaskMapper.INSTANCE.map(task);
    }

    public Set<Task> findAllBySubChapterId(String subCharterId) {
        return taskRepository.findAllBySubChapter_Id(UUID.fromString(subCharterId));
    }

    public Task getTaskByName(String name) {
        return taskRepository.findTaskByName(name).orElseThrow(() ->
                new NotFoundException(String.format("Task with NAME: %s not found", name)));
    }

    public Task findById(String id) {
        return taskRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new NotFoundException(String.format("Task with ID: %s not found", id)));
    }

}

