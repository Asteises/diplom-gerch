package ru.picker.commands;

import java.util.HashMap;
import java.util.Map;
import ru.picker.commands.callBackCommand.GetChapters;
import ru.picker.commands.callBackCommand.GetOneTask;
import ru.picker.commands.callBackCommand.GetSubChapters;
import ru.picker.commands.callBackCommand.GetTasks;
import ru.picker.core.service.impl.ChapterServiceImpl;
import ru.picker.core.service.impl.SubChapterServiceImpl;
import ru.picker.core.service.impl.TaskServiceImpl;

public class CommandMap {

    private final Map<String, TeleCommand> map = new HashMap<>();

    public CommandMap(ChapterServiceImpl chapterServiceImpl, SubChapterServiceImpl subChapterServiceImpl, TaskServiceImpl taskServiceImpl) {
        map.put("subChapter", new GetSubChapters(chapterServiceImpl));
        map.put("chapter", new GetChapters(chapterServiceImpl));
        map.put("back", new GetChapters(chapterServiceImpl));
        map.put("task", new GetTasks(subChapterServiceImpl));
        map.put("internalTask", new GetOneTask(taskServiceImpl));
    }


    public TeleCommand get(String callBack) {
        return map.get(callBack);
    }
}
