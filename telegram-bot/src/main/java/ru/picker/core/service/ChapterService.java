package ru.picker.core.service;

import ru.picker.core.model.ChapterDisplayDto;
import ru.picker.core.model.IncomeChapterDto;
import ru.picker.core.model.SubChapterDisplayDto;

import java.util.List;

public interface ChapterService {

    ChapterDisplayDto addChapter(IncomeChapterDto incomeChapterDto);
    ChapterDisplayDto getChapterById(String id);
    List<ChapterDisplayDto> getAllChapters();
    List<SubChapterDisplayDto> getSubChaptersByTheme(String theme);
    ChapterDisplayDto renewChapter(String id, IncomeChapterDto incomeChapterDto);
    void deleteChapter(String id);

}
