package ru.picker.commands.callBackCommand;

import lombok.RequiredArgsConstructor;
import ru.picker.commands.TeleCommand;
import ru.picker.core.entity.Task;
import ru.picker.core.service.TaskService;
import ru.picker.utils.TeleDto;

@RequiredArgsConstructor
public class GetOneTask implements TeleCommand {

    private final TaskService service;

    @Override
    public TeleDto execute(String theme) {
        TeleDto teleDto = new TeleDto();
        Task task = service.getTaskByName(theme);
        teleDto.setTest(task.getTest());
        teleDto.setCallBack("task-"+theme);
        return teleDto;
    }
}
