package ru.picker.core.controller;

import com.google.common.annotations.GwtCompatible;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.picker.core.model.IncomeTaskDto;
import ru.picker.core.model.TaskDto;
import ru.picker.core.service.TaskService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bot/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<TaskDto> addTask(@RequestBody IncomeTaskDto incomeTaskDto) {
        return ResponseEntity.ok(taskService.add(incomeTaskDto));
    }

    @GetMapping("/{}")
    public ResponseEntity<TaskDto> getTask(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.get(id));
    }
}
