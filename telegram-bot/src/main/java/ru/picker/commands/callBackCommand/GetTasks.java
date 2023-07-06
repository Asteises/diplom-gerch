package ru.picker.commands.callBackCommand;

import lombok.RequiredArgsConstructor;
import ru.picker.commands.TeleCommand;
import ru.picker.core.entity.Task;
import ru.picker.core.service.SubChapterService;
import ru.picker.utils.TeleDto;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetTasks implements TeleCommand {

    private final SubChapterService service;

    @Override
    public TeleDto execute(String theme) {
        TeleDto teleDto = new TeleDto();
        teleDto.setCallBack("internalTask");
        teleDto.setList(service.getAllTasksBySubChapter(theme)
            .stream()
            .map(Task::getName)
            .collect(Collectors.toList()));
        return teleDto;
    }
}
