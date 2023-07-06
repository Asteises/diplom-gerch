package ru.picker.core.service;

import com.google.common.collect.FluentIterable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.entity.Task;
import ru.picker.core.repository.SubChapterRepository;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubChapterService {

    private final SubChapterRepository subChapterRepository;

    public SubChapter findById(UUID id) {
        return subChapterRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("SubChapter with ID: %s not found", id)));
    }

    public Set<SubChapter> findAllByChapterId(UUID chapterId) {
        return subChapterRepository.findAllByChapter_Id(chapterId);
    }

    public List<Task> getAllTasksBySubChapter(String theme) {
        SubChapter subChapter = subChapterRepository.findSubChapterByName(theme).orElseThrow(() ->
                new NotFoundException(String.format("SubChapter with THEME: %s not found", theme)));

        return new ArrayList<>(subChapter.getTasks());
    }

    public List<SubChapter> findAll() {
        return subChapterRepository.findAll();
    }
}
