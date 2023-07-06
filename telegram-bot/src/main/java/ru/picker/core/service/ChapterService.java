package ru.picker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Chapter;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.repository.ChapterRepository;

import javax.ws.rs.NotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChapterService {

    private final ChapterRepository chapterRepository;

    public Chapter findById(UUID id) {
        return chapterRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Chapter with ID: %s not found", id)));
    }

    public Set<Chapter> getAllChapters() {
        return new HashSet<>(chapterRepository.findAll());
    }

    public List<SubChapter> getSubChaptersByTheme(String theme) {
        Chapter chapter = chapterRepository.findChapterByName(theme).orElseThrow(() ->
                new NotFoundException(String.format("Chapter with THEME: %s not found", theme)));

        return new ArrayList<>(chapter.getSubChapters());
    }
}


