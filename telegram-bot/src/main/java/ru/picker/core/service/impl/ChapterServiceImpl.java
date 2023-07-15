package ru.picker.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Chapter;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.mapper.ChapterMapper;
import ru.picker.core.mapper.SubChapterMapper;
import ru.picker.core.model.ChapterDisplayDto;
import ru.picker.core.model.IncomeChapterDto;
import ru.picker.core.model.SubChapterDisplayDto;
import ru.picker.core.repository.ChapterRepository;
import ru.picker.core.service.ChapterService;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;

    @Override
    public ChapterDisplayDto addChapter(IncomeChapterDto incomeChapterDto) {
        Chapter chapter = ChapterMapper.INSTANCE.map(incomeChapterDto);
        chapterRepository.save(chapter);
        return ChapterMapper.INSTANCE.map(chapter);
    }

    @Override
    public ChapterDisplayDto getChapterById(String id) {
        Chapter chapter = findChapterById(id);
        return ChapterMapper.INSTANCE.map(chapter);
    }

    @Override
    public List<ChapterDisplayDto> getAllChapters() {
        List<Chapter> chapters = chapterRepository.findAll();
        return ChapterMapper.INSTANCE.map(chapters);
    }

    @Override
    public List<SubChapterDisplayDto> getSubChaptersByTheme(String theme) {
        Chapter chapter = chapterRepository.findChapterByName(theme).orElseThrow(() ->
                new NotFoundException(String.format("Chapter with THEME: %s not found", theme)));
        Set<SubChapter> subChapters = chapter.getSubChapters();
        return new ArrayList<>(SubChapterMapper.INSTANCE.map(subChapters));
    }

    @Override
    public ChapterDisplayDto renewChapter(String id, IncomeChapterDto incomeChapterDto) {
        Chapter chapter = findChapterById(id);
        if (incomeChapterDto.getName() != null && !incomeChapterDto.getName().isBlank()) {
            chapter.setName(incomeChapterDto.getName());
            chapterRepository.save(chapter);
        }
        return ChapterMapper.INSTANCE.map(chapter);
    }

    @Override
    public void deleteChapter(String id) {
        Chapter chapter = findChapterById(id);
        if (chapter != null) {
            chapterRepository.delete(chapter);
        }
    }

    private Chapter findChapterById(String id) {
        return chapterRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new NotFoundException(String.format("Chapter with ID: %s not found", id)));
    }

}


