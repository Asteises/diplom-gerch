package ru.picker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Task;
import ru.picker.core.mapper.TaskMapper;
import ru.picker.core.model.IncomeTaskDto;
import ru.picker.core.model.TaskDisplayDto;
import ru.picker.core.repository.TaskRepository;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskDisplayDto add(IncomeTaskDto incomeTaskDto) {
        Task task = TaskMapper.INSTANCE.map(incomeTaskDto);
        taskRepository.save(task);
        return TaskMapper.INSTANCE.map(task);
    }

    public TaskDisplayDto get(String id) {
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

    public List<TaskDisplayDto> getAll() {
        List<Task> tasks = taskRepository.findAll();
        return TaskMapper.INSTANCE.map(tasks);
    }

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

    public void delete(String id) {
        Task task = findById(id);
        if (task != null) {
            taskRepository.delete(task);
        }
    }

    public Task findById(String id) {
        return taskRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new NotFoundException(String.format("Task with ID: %s not found", id)));
    }

}

