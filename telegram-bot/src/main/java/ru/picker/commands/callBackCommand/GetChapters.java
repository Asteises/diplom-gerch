package ru.picker.commands.callBackCommand;

import lombok.RequiredArgsConstructor;
import ru.picker.commands.TeleCommand;
import ru.picker.core.entity.Chapter;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.service.ChapterService;
import ru.picker.utils.TeleDto;

@RequiredArgsConstructor
public class GetChapters implements TeleCommand {

    private final ChapterService service;

    @Override
    public TeleDto execute(String theme) {
        TeleDto teleDto = new TeleDto();
        teleDto.setCallBack("task");
        teleDto.setList(service.getAllChapters()
            .stream()
            .map(Chapter::getName)
            .toList());
        return teleDto;
    }
}
