package ru.picker.core.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.repository.SubChapterRepository;

@Service
@RequiredArgsConstructor
public class SubChapterService {

    private final SubChapterRepository subChapterRepository;

    public List<SubChapter> getAllSubChapters() {
        return subChapterRepository.findAll();
    }
}
