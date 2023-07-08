package ru.picker.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.picker.core.model.IncomeTaskDto;
import ru.picker.core.model.TaskDisplayDto;
import ru.picker.core.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bot/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<TaskDisplayDto> addTask(@RequestBody IncomeTaskDto incomeTaskDto) {
        return ResponseEntity.ok(taskService.add(incomeTaskDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDisplayDto> getTask(@PathVariable String id) {
        return ResponseEntity.ok(taskService.get(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDisplayDto>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDisplayDto> putTask(@PathVariable String id,
                                                  @RequestBody IncomeTaskDto incomeTaskDto) {
        return ResponseEntity.ok(taskService.put(id, incomeTaskDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable String id) {
        taskService.delete(id);
        return ResponseEntity.ok(String.format("Task with ID: %s deleted", id));
    }
}
