package ru.picker.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Task;
import ru.picker.core.mapper.TaskMapper;
import ru.picker.core.model.IncomeTaskDto;
import ru.picker.core.model.TaskDisplayDto;
import ru.picker.core.repository.TaskRepository;
import ru.picker.core.service.TaskService;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskDisplayDto addTask(IncomeTaskDto incomeTaskDto) {
        Task task = TaskMapper.INSTANCE.map(incomeTaskDto);
        taskRepository.save(task);
        return TaskMapper.INSTANCE.map(task);
    }

    @Override
    public TaskDisplayDto getTaskById(String id) {
        Task task = findById(id);
        return TaskMapper.INSTANCE.map(task);
    }

    @Override
    public Task getTaskByName(String name) {
        return taskRepository.findTaskByName(name).orElseThrow(() ->
                new NotFoundException(String.format("Task with NAME: %s not found", name)));
    }

    @Override
    public List<TaskDisplayDto> getAll() {
        List<Task> tasks = taskRepository.findAll();
        return TaskMapper.INSTANCE.map(tasks);
    }

    @Override
    public TaskDisplayDto put(String id, IncomeTaskDto incomeTaskDto) {
        Task task = findById(id);
        if (incomeTaskDto.getName() != null && !incomeTaskDto.getName().isBlank()) {
            task.setName(incomeTaskDto.getName());
        }
        if (incomeTaskDto.getTest() != null && !incomeTaskDto.getTest().isBlank()) {
            task.setTest(incomeTaskDto.getTest());
        }
        if (incomeTaskDto.getAnswers() != null && !incomeTaskDto.getAnswers().isBlank()) {
            task.setAnswers(incomeTaskDto.getAnswers());
        }
        if (incomeTaskDto.getSubChapter() != null) {
            task.setSubChapter(incomeTaskDto.getSubChapter());
        }
        taskRepository.save(task);
        return TaskMapper.INSTANCE.map(task);
    }

    @Override
    public void delete(String id) {
        Task task = findById(id);
        if (task != null) {
            taskRepository.delete(task);
        }
    }

    private Task findById(String id) {
        return taskRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new NotFoundException(String.format("Task with ID: %s not found", id)));
    }

}

