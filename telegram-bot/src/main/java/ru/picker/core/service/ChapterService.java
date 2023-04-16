package ru.picker.core.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Chapter;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.repository.ChapterRepository;

@Service
@RequiredArgsConstructor
public class ChapterService {

    private final ChapterRepository chapterRepository;


    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    public List<SubChapter> getSubChaptersByTheme(String theme) {
        Chapter chapter = chapterRepository.findChapterByName(theme)
            .orElseThrow(() -> new RuntimeException("Chapter "+theme+" not found"));
        return chapter.getSubChapters().stream().toList();
    }
}
