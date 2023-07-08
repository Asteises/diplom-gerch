package ru.picker.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.picker.core.model.IncomeTaskDto;
import ru.picker.core.model.TaskDisplayDto;
import ru.picker.core.service.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bot/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<TaskDisplayDto> addTask(@RequestBody IncomeTaskDto incomeTaskDto) {
        return ResponseEntity.ok(taskService.add(incomeTaskDto));
    }

    @GetMapping("/{}")
    public ResponseEntity<TaskDisplayDto> getTask(@PathVariable String id) {
        return ResponseEntity.ok(taskService.get(id));
    }
}
