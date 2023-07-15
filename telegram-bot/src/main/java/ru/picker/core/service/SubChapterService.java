package ru.picker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.entity.Task;
import ru.picker.core.mapper.SubChapterMapper;
import ru.picker.core.model.IncomeSubChapterDto;
import ru.picker.core.model.SubChapterDisplayDto;
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

    public SubChapterDisplayDto add(IncomeSubChapterDto incomeSubChapterDto) {
        SubChapter subChapter = SubChapterMapper.INSTANCE.map(incomeSubChapterDto);

        return SubChapterMapper.INSTANCE.map(subChapterRepository.save(subChapter));
    }

    public SubChapterDisplayDto get(String id) {
        SubChapter subChapter = findById(id);
        return SubChapterMapper.INSTANCE.map(subChapter);
    }

    public Set<SubChapter> findAllByChapterId(String chapterId) {
        return subChapterRepository.findAllByChapter_Id(UUID.fromString(chapterId));
    }

    public List<Task> getAllTasksBySubChapter(String theme) {
        SubChapter subChapter = subChapterRepository.findSubChapterByName(theme).orElseThrow(() ->
                new NotFoundException(String.format("SubChapter with THEME: %s not found", theme)));

        return new ArrayList<>(subChapter.getTasks());
    }

    public List<SubChapter> findAll() {
        return subChapterRepository.findAll();
    }

    public SubChapterDisplayDto renewSubChapter(String id, IncomeSubChapterDto incomeSubChapterDto) {
        SubChapter subChapter = findById(id);
        if (incomeSubChapterDto.getName() != null && !incomeSubChapterDto.getName().isBlank()) {
            subChapter.setName(incomeSubChapterDto.getName());
        }
        if (incomeSubChapterDto.getChapter() != null) {
            subChapter.setChapter(incomeSubChapterDto.getChapter());
        }
        subChapterRepository.save(subChapter);
        return SubChapterMapper.INSTANCE.map(subChapter);
    }

    public void deleteSubChapter(String id) {
        SubChapter subChapter = findById(id);
        if (subChapter != null) {
            subChapterRepository.delete(subChapter);
        }
    }

    public SubChapter findById(String id) {

        return subChapterRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new NotFoundException(String.format("SubChapter with ID: %s not found", id)));
    }
}
