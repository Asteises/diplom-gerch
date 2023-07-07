package ru.picker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Chapter;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.mapper.ChapterMapper;
import ru.picker.core.model.ChapterDto;
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

    public ChapterDto add(IncomeChapterDto incomeChapterDto) {
        Chapter chapter = ChapterMapper.INSTANCE.map(incomeChapterDto);
        chapterRepository.save(chapter);
        return ChapterMapper.INSTANCE.map(
                chapter,
                theoryService,
                subChapterService);
    }

    public ChapterDto get(UUID id) {
        Chapter chapter = findById(id);
        return ChapterMapper.INSTANCE.map(
                chapter,
                theoryService,
                subChapterService);
    }

    public Set<Chapter> getAllChapters() {
        return new HashSet<>(chapterRepository.findAll());
    }

    public List<SubChapter> getSubChaptersByTheme(String theme) {
        Chapter chapter = chapterRepository.findChapterByName(theme).orElseThrow(() ->
                new NotFoundException(String.format("Chapter with THEME: %s not found", theme)));

        return new ArrayList<>(chapter.getSubChapters());
    }

    public ChapterDto renewChapter(UUID id, IncomeChapterDto incomeChapterDto) {
        Chapter chapter = findById(id);
        if (incomeChapterDto.getName() != null && !incomeChapterDto.getName().isBlank()) {
            chapter.setName(incomeChapterDto.getName());
            chapterRepository.save(chapter);
        }
        return ChapterMapper.INSTANCE.map(
                chapter,
                theoryService,
                subChapterService);
    }

    public void deleteChapter(UUID id) {
        Chapter chapter = findById(id);
        chapterRepository.delete(chapter);
    }

    public Chapter findById(UUID id) {
        return chapterRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Chapter with ID: %s not found", id)));
    }
}


