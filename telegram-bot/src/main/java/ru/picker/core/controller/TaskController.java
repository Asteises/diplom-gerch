package ru.picker.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.picker.core.service.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bot/task")
public class TaskController {

    private final TaskService taskService;
}
