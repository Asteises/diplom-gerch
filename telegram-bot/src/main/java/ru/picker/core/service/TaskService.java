package ru.picker.core.service;

import ru.picker.core.entity.Task;
import ru.picker.core.model.IncomeTaskDto;
import ru.picker.core.model.TaskDisplayDto;

import java.util.List;

public interface TaskService {

    TaskDisplayDto addTask(IncomeTaskDto incomeTaskDto);
    TaskDisplayDto getTaskById(String id);
    Task getTaskByName(String name);
    List<TaskDisplayDto> getAll();
    TaskDisplayDto put(String id, IncomeTaskDto incomeTaskDto);
    void delete(String id);

}
