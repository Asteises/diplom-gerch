package ru.picker.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.picker.core.model.IncomeTaskDto;
import ru.picker.core.model.TaskDisplayDto;
import ru.picker.core.service.impl.TaskServiceImpl;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bot/task")
public class TaskController {

    private final TaskServiceImpl taskServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<TaskDisplayDto> addTask(@RequestBody @NotNull IncomeTaskDto incomeTaskDto) {
        return ResponseEntity.ok(taskServiceImpl.addTask(incomeTaskDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDisplayDto> getTask(@PathVariable String id) {
        return ResponseEntity.ok(taskServiceImpl.getTaskById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDisplayDto>> getAll() {
        return ResponseEntity.ok(taskServiceImpl.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDisplayDto> putTask(@PathVariable String id,
                                                  @RequestBody @NotNull IncomeTaskDto incomeTaskDto) {
        return ResponseEntity.ok(taskServiceImpl.put(id, incomeTaskDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable String id) {
        taskServiceImpl.delete(id);
        return ResponseEntity.ok(String.format("Task with ID: %s deleted", id));
    }
}
