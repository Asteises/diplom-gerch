package ru.picker.core.service;

import ru.picker.core.entity.Task;
import ru.picker.core.model.IncomeSubChapterDto;
import ru.picker.core.model.SubChapterDisplayDto;

import java.util.List;

public interface SubChapterService {

    SubChapterDisplayDto addSubChapter(IncomeSubChapterDto incomeSubChapterDto);
    SubChapterDisplayDto getSubChapterById(String id);
    List<Task> getAllTasksBySubChapter(String theme);
    List<SubChapterDisplayDto> findAll();
    SubChapterDisplayDto renewSubChapter(String id, IncomeSubChapterDto incomeSubChapterDto);
    void deleteSubChapter(String id);

}
