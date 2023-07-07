package ru.picker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Chapter;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.entity.Task;
import ru.picker.core.mapper.SubChapterMapper;
import ru.picker.core.model.IncomeSubChapterDto;
import ru.picker.core.model.SubChapterDto;
import ru.picker.core.repository.SubChapterRepository;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubChapterService {

    private final SubChapterRepository subChapterRepository;
    private final ChapterService chapterService;
    private final TaskService taskService;

    public SubChapterDto add(IncomeSubChapterDto incomeSubChapterDto) {
        SubChapter subChapter = SubChapterMapper.INSTANCE.map(
                incomeSubChapterDto,
                chapterService);

        return SubChapterMapper.INSTANCE.map(
                subChapterRepository.save(subChapter),
                taskService);
    }

    public SubChapterDto get(UUID id) {
        SubChapter subChapter = findById(id);
        return SubChapterMapper.INSTANCE.map(subChapter, taskService);
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

    public SubChapterDto renewSubChapter(UUID id, IncomeSubChapterDto incomeSubChapterDto) {
        SubChapter subChapter = findById(id);
        if (incomeSubChapterDto.getName() != null && !incomeSubChapterDto.getName().isBlank()) {
            subChapter.setName(incomeSubChapterDto.getName());
        }
        if (incomeSubChapterDto.getChapterId() != null) {
            Chapter chapter = chapterService.findById(incomeSubChapterDto.getChapterId());
            subChapter.setChapter(chapter);
        }
        subChapterRepository.save(subChapter);
        return SubChapterMapper.INSTANCE.map(subChapter, taskService);
    }

    public void deleteSubChapter(UUID id) {
        SubChapter subChapter = findById(id);
        subChapterRepository.delete(subChapter);
    }

    public SubChapter findById(UUID id) {

        return subChapterRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("SubChapter with ID: %s not found", id)));
    }
}
