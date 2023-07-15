package ru.picker.commands.callBackCommand;

import lombok.RequiredArgsConstructor;
import ru.picker.commands.TeleCommand;
import ru.picker.core.model.ChapterDisplayDto;
import ru.picker.core.service.impl.ChapterServiceImpl;
import ru.picker.utils.TeleDto;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetChapters implements TeleCommand {

    private final ChapterServiceImpl service;

    @Override
    public TeleDto execute(String theme) {
        TeleDto teleDto = new TeleDto();
        teleDto.setCallBack("subChapter");
        teleDto.setList(service.getAllChapters()
                .stream()
                .map(ChapterDisplayDto::getName)
                .collect(Collectors.toList()));
        return teleDto;
    }
}
