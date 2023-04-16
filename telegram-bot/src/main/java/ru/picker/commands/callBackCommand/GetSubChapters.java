package ru.picker.commands.callBackCommand;

import lombok.RequiredArgsConstructor;
import ru.picker.commands.TeleCommand;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.entity.Task;
import ru.picker.core.service.ChapterService;
import ru.picker.core.service.SubChapterService;
import ru.picker.utils.TeleDto;

@RequiredArgsConstructor
public class GetSubChapters implements TeleCommand {

    private final ChapterService service;

    @Override
    public TeleDto execute(String theme) {
        TeleDto teleDto = new TeleDto();
        teleDto.setCallBack("task");
        teleDto.setList(service.getSubChaptersByTheme(theme)
            .stream()
            .map(SubChapter::getName)
            .toList());
        return teleDto;
    }
}
