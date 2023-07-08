package ru.picker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Chapter;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.entity.Theory;
import ru.picker.core.mapper.ChapterMapper;
import ru.picker.core.model.ChapterDisplayDto;
import ru.picker.core.model.IncomeChapterDto;
import ru.picker.core.repository.ChapterRepository;

import javax.ws.rs.NotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChapterService {

    private final ChapterRepository chapterRepository;
    private final SubChapterService subChapterService;
    private final TheoryService theoryService;

    public ChapterDisplayDto add(IncomeChapterDto incomeChapterDto) {
        Chapter chapter = ChapterMapper.INSTANCE.map(incomeChapterDto);
        chapterRepository.save(chapter);
        return ChapterMapper.INSTANCE.map(chapter);
    }

    public ChapterDisplayDto get(String id) {
        Chapter chapter = findById(id);
        return ChapterMapper.INSTANCE.map(chapter);
    }

    public Set<Chapter> getAllChapters() {
        return new HashSet<>(chapterRepository.findAll());
    }

    public List<SubChapter> getSubChaptersByTheme(String theme) {
        Chapter chapter = chapterRepository.findChapterByName(theme).orElseThrow(() ->
                new NotFoundException(String.format("Chapter with THEME: %s not found", theme)));

        return new ArrayList<>(chapter.getSubChapters());
    }

    public ChapterDisplayDto renewChapter(String id, IncomeChapterDto incomeChapterDto) {
        Chapter chapter = findById(id);
        if (incomeChapterDto.getName() != null && !incomeChapterDto.getName().isBlank()) {
            chapter.setName(incomeChapterDto.getName());
            chapterRepository.save(chapter);
        }
        return ChapterMapper.INSTANCE.map(chapter);
    }

    public void deleteChapter(String id) {
        Chapter chapter = findById(id);
        chapterRepository.delete(chapter);
    }

    public Chapter findById(String id) {
        return chapterRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new NotFoundException(String.format("Chapter with ID: %s not found", id)));
    }

    public Set<SubChapter> setSubChapters(Chapter chapter) {
        return subChapterService.findAllByChapterId(chapter.getId().toString());
    }

    public Set<Theory> setTheories(Chapter chapter) {
        return theoryService.findAllByChapterId(chapter.getId());
    }

}


