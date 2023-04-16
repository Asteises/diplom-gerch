package ru.picker.commands;

import java.util.HashMap;
import java.util.Map;
import ru.picker.commands.callBackCommand.GetChapters;
import ru.picker.commands.callBackCommand.GetSubChapters;
import ru.picker.commands.callBackCommand.GetTasks;
import ru.picker.core.service.ChapterService;
import ru.picker.core.service.SubChapterService;

public class CommandMap {

    private final Map<String, TeleCommand> map = new HashMap<>();

    public CommandMap(ChapterService chapterService, SubChapterService subChapterService) {
        map.put("subChapter", new GetSubChapters(chapterService));
        map.put("chapter", new GetChapters(chapterService));
        map.put("back", new GetChapters(chapterService));
        map.put("task", new GetTasks(subChapterService));
    }


    public TeleCommand get(String callBack) {
        return map.get(callBack);
    }
}
